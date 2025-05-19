package co.in.nextgencoder.springblueprint.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterRequestDTO {
    private String email;
    private String phoneNumber;
    private String password;

    public String getUserIdentity() {
        return doesPhoneExists() ? this.phoneNumber : this.email;
    }

    private boolean doesPhoneExists() {
        return this.getPhoneNumber() != null && !this.getPhoneNumber().isBlank();
    }
}