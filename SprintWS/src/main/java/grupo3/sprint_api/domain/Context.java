package grupo3.sprint_api.domain;

import grupo3.sprint_api.exception.AppKeyInvalidaException;

import java.util.Random;

public class Context {

    private String context;
    private boolean valid;

    private static final String APP_KEY = "UPSKILL_KEY";

    public Context (String appKey) {
        if (appKey.equals(APP_KEY)) {
            setContext(generateContext());
            setValid(true);
        } else {
            throw new AppKeyInvalidaException("App Key inválida");
        }
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
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
