package org.arqui.grupo9.microserviciocuentas.services.exceptions;

/**
 * Excepción `CuentaInhabilitadaException`.
 *
 * <p>Se lanza cuando se intenta acceder a una cuenta que ha sido inhabilitada,
 * indicando que el usuario no puede realizar operaciones en esta cuenta debido
 * a una restricción temporal o permanente.</p>
 *
 * <p><strong>Ejemplo de uso:</strong></p>
 * <pre>
 * if (cuenta.isInhabilitada()) {
 *     throw new CuentaInhabilitadaException(
 *         "La cuenta está inhabilitada debido a múltiples intentos fallidos de acceso.",
 *         "Su cuenta ha sido temporalmente inhabilitada. Intente nuevamente más tarde.",
 *         "high"
 *     );
 * }
 * </pre>
 *
 * <p>Esta excepción es útil en servicios de gestión de cuentas, donde se requiere
 * bloquear el acceso o uso de cuentas inhabilitadas.</p>
 */
public class CuentaInhabilitadaException extends MyException {
  public CuentaInhabilitadaException(String message, String userMessage, String severity) {
    super(message, userMessage, severity);
  }
}

