package grupo3.sprint_api.dto;

public class SessionDTO {

    private UserDTO user;

    private String logindate;

    public SessionDTO(){};

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getLogindate() {
        return logindate;
    }

    public void setLogindate(String logindate) {
        this.logindate = logindate;
    }
}
