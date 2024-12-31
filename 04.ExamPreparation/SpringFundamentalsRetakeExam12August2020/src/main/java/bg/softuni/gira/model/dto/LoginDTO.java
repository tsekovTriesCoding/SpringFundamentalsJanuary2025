package bg.softuni.gira.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginDTO {
    @Size(min = 5, max = 20, message = "Email length must be between 3 and 20 characters")
    @NotBlank(message = "Email can't be empty!")
    private String email;

    @Size(min = 3, message = "Password length must be between 3 and 20 characters")
    @NotNull
    private String password;

    public LoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public LoginDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
