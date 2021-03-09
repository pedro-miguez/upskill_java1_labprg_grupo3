package grupo3.sprint_api.dto;

/**
 * This will be used for serialization and deserialization of the class Context.
 */
public class ContextDTO {

    private String appContext;

    public ContextDTO() {};

    public void setAppContext(String appContext) {
        this.appContext = appContext;
    }

    public String getAppContext() {
        return this.appContext;
    }
}
