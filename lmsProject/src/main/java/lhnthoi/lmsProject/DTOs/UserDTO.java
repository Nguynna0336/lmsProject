package lhnthoi.lmsProject.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @JsonProperty("username")
    private String userName;
    @JsonProperty("fullname")
    private String fullName;
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "Email is required")
    private String email;
    @JsonProperty("dob")
    private Date dateOfBirth;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @JsonProperty("retype_password")
    @NotBlank(message = "Retype password cannot be blank")
    private String retypePassword;

}
