package co.in.nextgencoder.learningpath.service.exception.validation;

import co.in.nextgencoder.learningpath.service.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmailAddressException extends BadRequestException {
    public InvalidEmailAddressException(String email) {
        super("Invalid email : " + email);
    }
}
