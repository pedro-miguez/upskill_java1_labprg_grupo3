package grupo3.sprint_api.dto;

/**
 * This represents a "clone" of the model (domain) class 'Context'.
 * (Because model classes cannot be exposed to outside)
 * This will be used for serialization and deserialization of the class Context.
 */
public class ContextDTO {

    private String appContext;

    /**
     * Represents an empty constructor.
     */
    public ContextDTO() {};

    /**
     * Sets an context of the app.
     *
     * @param appContext
     */
    public void setAppContext(String appContext) {
        this.appContext = appContext;
    }

    /**
     * Gets an context of the app.
     *
     * @return appContext
     */
    public String getAppContext() {
        return this.appContext;
    }
}
