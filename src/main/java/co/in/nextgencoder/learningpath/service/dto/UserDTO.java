package co.in.nextgencoder.learningpath.service.dto;

import co.in.nextgencoder.learningpath.domain.enumeration.Gender;
import co.in.nextgencoder.learningpath.domain.enumeration.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private Integer age;
}