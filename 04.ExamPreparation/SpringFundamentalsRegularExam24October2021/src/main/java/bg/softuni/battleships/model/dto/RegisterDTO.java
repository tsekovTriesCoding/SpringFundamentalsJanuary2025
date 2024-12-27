package bg.softuni.battleships.model.dto;

import bg.softuni.battleships.vallidation.annotation.UniqueEmail;
import bg.softuni.battleships.vallidation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Username must be between 3 and 10 characters.")
    @UniqueUsername
    private String username;

    @NotNull
    @Size(min = 5, max = 20, message = "Full name must be between 5 and 20 characters.")
    private String fullName;

    @UniqueEmail
    @Email(message = "Enter valid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @Size(min = 3, max = 20, message = "Password length must be more than 3 characters.")
    @NotNull
    private String password;

    @Size(min = 3, max = 20, message = "Confirm password length must be more than 3 characters.")
    @NotNull
    private String confirmPassword;

    public RegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public RegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public RegisterDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
