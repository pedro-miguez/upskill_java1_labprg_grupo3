package grupo3.sprint_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.web.bind.annotation.ResponseBody;

public class EmailDTO {

    private String email;

    public EmailDTO() {};

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
