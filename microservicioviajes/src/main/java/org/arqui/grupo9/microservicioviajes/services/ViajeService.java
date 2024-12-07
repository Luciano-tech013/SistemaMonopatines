package org.arqui.grupo9.microservicioviajes.services;

import feign.FeignException;
import org.arqui.grupo9.microservicioviajes.clients.CuentaFeignClient;
import org.arqui.grupo9.microservicioviajes.clients.MonopatinFeignClient;
import org.arqui.grupo9.microservicioviajes.model.Viaje;
import org.arqui.grupo9.microservicioviajes.repository.IViajeRepository;
import org.arqui.grupo9.microservicioviajes.services.dtos.CuentaMpDTO;
import org.arqui.grupo9.microservicioviajes.services.dtos.MonopatinDTO;
import org.arqui.grupo9.microservicioviajes.services.dtos.ReporteTiempoTotalPausadoDTO;
import org.arqui.grupo9.microservicioviajes.services.exceptions.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ViajeService {
    private IViajeRepository repository;
    private CuentaFeignClient cuentaClient;
    private MonopatinFeignClient monopatinClient;
    private static double creditoDescontado;
    private static double kmsRecorridos;
    private static Viaje viajeGenerado;
    private static AtomicBoolean viajeEnCurso;
    private static AtomicBoolean viajePausado;
    private static AtomicBoolean viajePausadoConRecargo;
    private static double nuevoPrecio;
    private static LocalDate fechaNueva;
    private CuentaMpDTO cuentaMP;
    private CuentaSistemaDTO cuentaSistema;
    private MonopatinDTO monopatin;
    private final String URL_DECODIFICADA = "/generar/cuenta/{idCuentaSistema}/monopatin/{idMonopatin}";

    public ViajeService(IViajeRepository repository, @Lazy CuentaFeignClient cuentaClient, @Lazy MonopatinFeignClient monopatinClient) {
        this.repository = repository;
        this.cuentaClient = cuentaClient;
        this.monopatinClient = monopatinClient;
        viajeEnCurso = new AtomicBoolean(false);
        viajePausado = new AtomicBoolean(false);
        viajePausadoConRecargo = new AtomicBoolean(false);
    }

    public List<Viaje> findAll() {
        return this.repository.findAll();
    }

    public Viaje findById(Long idViaje) {
        Optional<Viaje> viaje = this.repository.findById(idViaje);
        if(viaje.isPresent())
            return viaje.get();

        throw new NotFoundViajeException("El id enviado es incorrecto, no existe en la tabla viaje", "El viaje solicitado nunca fue generado. Por favor, solicita un viaje finalizado", "low");
    }

    public boolean save(Viaje viaje) {
        this.repository.save(viaje);
        return true;
    }

    public boolean deleteById(Long idViaje) {
        this.findById(idViaje);
        this.repository.deleteById(idViaje);
        return true;
    }

    //SIMULA EL ESCANEO DE UN CODIGO QR
    public boolean realizar(Long idCuentaSistema, Long idMonopatin) {
        try {
            cuenta = this.cuentaClient.findById(idCuentaSistema).getBody();
            if(!cuenta.estaHabilitada)
                throw new CuentaInhabilitadaException("La cuenta no esta habiltiada", "La cuenta con la que intentas operar no se encuentra habilitada", "high");

        } catch(FeignException.FeignClientException ex) {
            throw new NotFoundCuentaClientException("La cuenta no está en el sistema", "No se pudo generar el viaje. Verifica los datos.", "high");
        }

        try {
            cuentaMP = this.cuentaMPClient.findById().getBody();
        }catch(FeignException.FeignClientException ex) {
            throw new NotFoundCuentaMPException("", "", "");
        }

        try {
            monopatin = monopatinClient.findById(idMonopatin).getBody();
        } catch(FeignException.FeignClientException ex) {
            throw new NotFoundMonopatinClientException("El monopatín no está en el sistema", "No se pudo generar el viaje. Verifica el monopatín.", "high");
        }

        if(cuentaMP.getCredito() < 0)
            throw new SaldoInsuficienteException("El usuario no tiene suficiente saldo en su cuenta", "No tienes suficiente saldo para realizar un viaje", "high");

        String qr = monopatin.getCodigoQR();
        if(qr == null || qr.isEmpty())
            throw new NotValidCodigoQRException("El codigo QR obtenido no es valido", "El Codigo QR de este monopatin es incorrecto. Por favor, realiza el reclamo", "high");

        String qrPrimeraDecodificacion = new String(Base64.getDecoder().decode(qr), StandardCharsets.UTF_8);
        String qrDecodificado = new String(Base64.getDecoder().decode(qrPrimeraDecodificacion), StandardCharsets.UTF_8);
        if(!qrDecodificado.equals(URL_DECODIFICADA))
            throw new NotValidCodigoQRException("El escaneo del QR fallo", "El Codigo QR escaneado no es valido", "high");

        //LLAMAR AL ENDPOINT DE MONOPATIN QUE SIMULA ESCENDER EL MONOPATIN (ACTIVARLO)

        if(!this.generar(cuenta.getIdCuentaSistema(), monopatin.getIdMonopatin()))
            throw new ViajeException("No se puedo generar un viaje", "No se pudo generar un viaje luego del escaneo. Vuelve a intentar", "high");

        return true;
    }

    private boolean generar(Long idCuentaSistema, Long idMonopatin) {
        //Generar el viaje
        viajeGenerado = new Viaje(LocalDateTime.now(), idCuentaSistema, idMonopatin);

        if(fechaNueva != null && (LocalDate.now().isEqual(fechaNueva) || LocalDate.now().isAfter(fechaNueva)))
            viajeGenerado.setPrecio(nuevoPrecio);

        creditoDescontado = 0;
        kmsRecorridos = 0;

        // Activar la tarea programada
        viajeEnCurso.set(true);

        return true;
    }

    public Viaje pausar() {
        viajePausado.set(true);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Si el viaje sigue estando pausado
                if(viajePausado.get())
                    viajePausadoConRecargo.set(true);
            }
        };

        viajeGenerado.setCostoTotal(creditoDescontado);
        viajeGenerado.setKmsRecorridos(kmsRecorridos);
        //timer.schedule(task, 900000);
        timer.schedule(task, 3000);
        return viajeGenerado;
    }

    public Viaje despausar() {
        if(viajePausadoConRecargo.get())
            viajePausadoConRecargo.set(false);

        viajePausado.set(false);
        viajeGenerado.setCostoTotal(creditoDescontado);
        viajeGenerado.setKmsRecorridos(kmsRecorridos);
        return viajeGenerado;
    }

    @Scheduled(fixedRate = 1000)
    public synchronized void actualizarViajeEnCurso() {
        if (!viajeEnCurso.get())
            return;

        double incrementoCredito = Viaje.PRECIO_BASE;

        // Si el viaje está pausado, verifica si aplica recargo
        if (viajePausado.get()) {
            incrementoCredito += viajePausadoConRecargo.get() ? Viaje.PRECIO_RECARGO : 0;
        } else {
            // Solo aumenta kilómetros si el viaje no está pausado
            kmsRecorridos += 1;
        }

        creditoDescontado += incrementoCredito;
    }


    public Viaje finalizar() {
        if (!viajeEnCurso.get())
            throw new FinalizarViajeException("Se intentó finalizar un viaje que no fue generado aún", "Para finalizar un viaje, primero debes generarlo.", "high");

        viajeEnCurso.set(false);  // Detener la tarea programada
        viajeGenerado.setFechaFinViaje(LocalDateTime.now());
        viajeGenerado.setCostoTotal(creditoDescontado);
        viajeGenerado.setKmsRecorridos(kmsRecorridos);

        //aplicar credito descontado a la cuenta del usuario
        cuenta.setCredito(cuenta.getCredito() - creditoDescontado);

        //aplicar los kms recorridos al monopatin
        monopatin.setKmsRecorridos(monopatin.getKmsRecorridos() + kmsRecorridos);

        //this.cuentaClient.save(cuenta);
        this.monopatinClient.save(monopatin);

        if(this.save(viajeGenerado))
            return this.findById(viajeGenerado.getIdViaje());

        return null;
    }

    public List<MonopatinDTO> getMonopatinesByCantViajesInOneYear(int anio, int cantViajes) {
        List<Long> idMonopatines = this.repository.getMonopatinesByCantViajesInOneYear(anio, cantViajes);

        List<MonopatinDTO> monopatinesObtenidos = new LinkedList<>();
        for(Long id : idMonopatines) {
            monopatinesObtenidos.add(monopatinClient.findById(id).getBody());
        }

        return monopatinesObtenidos;
    }

    public double getTotalFacturadoByMesesInOneYear(int mesIni, int mesFin, int anio) {
        try {
            return this.repository.getTotalFacturadoByMesesInOneYear(mesIni, mesFin, anio);
        } catch(Exception e) {
            throw new NotFoundFechaException("Los meses o el año enviado no estaban cargados en el sistema", "No se encontro ningun viaje registrado en la fecha de facturacion enviada. Por favor, envia otra fecha", "high");
        }
    }

    public boolean ajustarPrecioParaUnaFecha(LocalDate fecha, double precio) {
        nuevoPrecio = precio;
        fechaNueva = fecha;
        return true;
    }

    public ReporteTiempoTotalPausadoDTO getTiempoTotalPausadoDeMonopatin(Long idMonopatin) {
        //obtengo todos los tiempos de pausa de ese monopatin (inicio y fin)
        List<Viaje> datos = this.repository.getTiemposPausadosDeMonopatin(idMonopatin);

        Duration tiempoTotal = Duration.ZERO;
        // Calcular el tiempo TOTAL de pausa
        for (Viaje viaje : datos) {
            System.out.println(viaje);
            LocalDateTime fin = viaje.getFechaFinViajeConPausa();
            LocalDateTime inicio = viaje.getFechaIniViajeConPausa();

            if (fin == null && inicio == null) {
                return null;
            }

            if(inicio.isAfter(fin)) {
                throw new NotFoundFechaException("El viaje encontrado no tiene fecha valida de inicio y fin", "La fecha del viaje encontrado es incorrecta", "high");
            }

            tiempoTotal = Duration.between(inicio, fin);;
        }

        long horas = tiempoTotal.toHours();                // Obtiene las horas completas
        long minutos = tiempoTotal.toMinutes();

        return new ReporteTiempoTotalPausadoDTO(horas, minutos);
    }

    //METODOS PARA EL TESTING
    public AtomicBoolean isViajeEnCurso() {
        return viajeEnCurso;
    }

    public double getCreditoDescontado() {
        return creditoDescontado;
    }

    public double getKmsRecorridos() {
        return kmsRecorridos;
    }
}
