package co.in.nextgencoder.springblueprint.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RegisterResponseDTO {
    private Long id;
    private String email;
    private String phoneNumber;

    public String getUserIdentity() {
        return doesPhoneExists() ? this.phoneNumber : this.email;
    }

    private boolean doesPhoneExists() {
        return this.getPhoneNumber() != null && !this.getPhoneNumber().isBlank();
    }
}