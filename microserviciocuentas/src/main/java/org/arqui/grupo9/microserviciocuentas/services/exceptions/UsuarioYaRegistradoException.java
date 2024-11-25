package org.arqui.grupo9.microserviciocuentas.services.exceptions;

/**
 * Excepción `UsuarioYaRegistradoException`.
 *
 * <p>Esta excepción se lanza cuando se intenta registrar un usuario que ya
 * existe en el sistema, evitando duplicación de registros.</p>
 *
 * <p><strong>Ejemplo de uso:</strong></p>
 * <pre>
 * if (usuarioService.existeUsuario(email)) {
 *     throw new UsuarioYaRegistradoException(
 *         "El usuario con el correo especificado ya está registrado.",
 *         "Este usuario ya está registrado. Intente con otro correo.",
 *         "high"
 *     );
 * }
 * </pre>
 *
 * <p>Esta excepción se utiliza en los servicios de registro de usuarios para
 * prevenir la duplicación y mantener la integridad de los datos.</p>
 */
public class UsuarioYaRegistradoException extends MyException {
    public UsuarioYaRegistradoException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}


