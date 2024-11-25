package org.arqui.grupo9.microserviciocuentas.services.exceptions;

/**
 * Excepción lanzada cuando no se encuentra una fecha específica en el sistema.
 * <p>
 * Esta excepción se utiliza para indicar que una operación ha fallado debido a que
 * no se encontró la fecha solicitada. Proporciona un mensaje técnico para los registros,
 * un mensaje amigable para el usuario y un nivel de severidad para su tratamiento.
 * </p>
 *
 * @see MyException
 */
public class NotFoundFechaException extends MyException {

  /**
   * Crea una nueva instancia de {@code NotFoundFechaException} con un mensaje técnico,
   * un mensaje amigable para el usuario y un nivel de severidad.
   *
   * @param message     Mensaje técnico para los registros internos del sistema, detallando
   *                    la causa específica de la excepción.
   * @param userMessage Mensaje que será mostrado al usuario final, proporcionando
   *                    una explicación clara y comprensible del problema.
   * @param severity    Nivel de severidad de la excepción, que puede ser utilizado
   *                    para categorizar y manejar el error según su importancia.
   */
  public NotFoundFechaException(String message, String userMessage, String severity) {
    super(message, userMessage, severity);
  }
}

