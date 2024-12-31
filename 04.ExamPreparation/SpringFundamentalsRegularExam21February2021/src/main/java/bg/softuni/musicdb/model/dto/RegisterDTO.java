package bg.softuni.musicdb.model.dto;

import bg.softuni.musicdb.vallidation.annotation.UniqueEmail;
import bg.softuni.musicdb.vallidation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    @UniqueUsername
    private String username;

    @NotNull
    @Size(min = 3, max = 20, message = "Full name length must be between 3 and 20 characters")
    private String fullName;

    @UniqueEmail
    @Email(message = "Must be a valid email.")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters")
    @NotNull
    private String password;

    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters")
    @NotNull
    private String confirmPassword;

    public RegisterDTO() {
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public RegisterDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
