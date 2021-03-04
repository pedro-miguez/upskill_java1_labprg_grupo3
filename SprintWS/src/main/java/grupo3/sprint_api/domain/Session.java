package grupo3.sprint_api.domain;

import java.time.LocalDateTime;

/**
 * Current class implements the tools to create new sessions (as Session).
 */
public class Session {

    private User user;
    private Context context;
    private Data loginDate;

    /**
     * Instantiates a new Session with set parameters.
     *
     * @param user
     * @param context
     */
    public Session(User user, Context context) {
        setUser(user);
        setContext(context);
        setDate(Data.dataAtual());
    }

    /**
     * Instantiates a new Session with set parameters.
     *
     * @param user
     * @param context
     * @param dataLogin
     */
    public Session(User user, Context context, Data dataLogin){
        setUser(user);
        setContext(context);
        setDate(dataLogin);
    }

    /**
     * Gets an User.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets an User.
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets a Context.
     *
     * @return context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets a Context.
     *
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Gets a Login Date.
     *
     * @return loginDate
     */
    public Data getDate() {
        return loginDate;
    }

    /**
     * Sets a Login Date.
     *
     * @param date
     */
    public void setDate(Data date) {
        this.loginDate = date;
    }

    /**
     * Sets an invalid context.
     */
    public void setContextInvalid() {
        this.context.setValid(false);
    }

    /**
     * Boolean function to check if an context is valid.
     *
     * @return boolean
     */
    public boolean isValid() {
        return this.context.isValid();
    }

}
