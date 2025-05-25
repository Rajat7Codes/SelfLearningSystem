package co.in.nextgencoder.springblueprint.controller;

import co.in.nextgencoder.springblueprint.domain.AppUser;
import co.in.nextgencoder.springblueprint.service.AuthService;
import co.in.nextgencoder.springblueprint.service.UserService;
import co.in.nextgencoder.springblueprint.service.dto.LoginRequestDTO;
import co.in.nextgencoder.springblueprint.service.dto.LoginResponseDTO;
import co.in.nextgencoder.springblueprint.service.dto.RegisterRequestDTO;
import co.in.nextgencoder.springblueprint.service.dto.RegisterResponseDTO;
import co.in.nextgencoder.springblueprint.service.exception.validation.InvalidEmailAddressException;
import co.in.nextgencoder.springblueprint.service.exception.validation.InvalidPhoneNumberException;
import co.in.nextgencoder.springblueprint.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hello")
public class HelloController {


    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping
    public ResponseEntity<String> sayHello() {

        return ResponseEntity.ok("Hello");
    }
}
