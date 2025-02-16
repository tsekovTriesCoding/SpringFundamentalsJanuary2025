package app.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Builder
@Data
public class EditProfileRequest {

    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters!")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters!")
    private String lastName;

    @Email(message = "Enter valid email address")
    private String email;

    @URL
    private String profilePicture;
}
