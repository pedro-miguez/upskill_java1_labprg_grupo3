package grupo3.sprint_api.exception;

/**
 * This class is called whenever a login was not processed successfully.
 *
 * @author Grupo 3 - Turma 1
 */
public class LoginErradoException extends IllegalArgumentException {
    public LoginErradoException(String s) {
        super(s);
    }

}
