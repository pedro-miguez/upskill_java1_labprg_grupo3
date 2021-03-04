package grupo3.sprint_api.domain;

import java.time.LocalDateTime;

public class Session {

    private User user;
    private Context context;
    private Data loginDate;

    public Session(User user, Context context) {
        setUser(user);
        setContext(context);
        setDate(Data.dataAtual());
    }

    public Session(User user, Context context, Data dataLogin){
        setUser(user);
        setContext(context);
        setDate(dataLogin);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Data getDate() {
        return loginDate;
    }

    public void setDate(Data date) {
        this.loginDate = date;
    }

    public void setContextInvalid() {
        this.context.setValid(false);
    }

    public boolean isValid() {
        return this.context.isValid();
    }

}
