package grupo3.sprint_api.domain;

import grupo3.sprint_api.exception.AppKeyInvalidaException;
import grupo3.sprint_api.persistence.RepositorioAuth;

import java.sql.SQLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class allows you to obtain a context key that identifies a user session.
 * This context key will be needed to run all of the remaining methods available.
 * This key is valid for a period of time (in milliseconds) which is returned by
 * the API after obtaining the context, until it is a login or user registration
 * is performed. If the login or registration is not carried out user within the
 * specified period of time, a new context key must be obtained.
 */
public class Context {

    private String context;
    private boolean valid;

    private static final String APP_KEY = "IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06";

    public Context (String appKey) {
        if (appKey.equals(APP_KEY)) {
            setContext(generateContext());
            setValid(true);
            scheduleCheckTimeout();
        } else {
            throw new AppKeyInvalidaException("App Key inválida");
        }
    }

    public Context (String contextKey, boolean valido) {
        this.context = contextKey;
        this.valid = valido;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String generateContext() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public void scheduleCheckTimeout() {
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    RepositorioAuth.getInstance().checkTimeout(getContext());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 300000L;
        timer.schedule(task, delay);
    }
}
