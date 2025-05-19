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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {


    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO userLogin) {
        if (userLogin.getPhoneNumber() != null && !userLogin.getPhoneNumber().isBlank() &&
                !Validator.validatePhoneNumber(userLogin.getPhoneNumber())) {
            throw new InvalidPhoneNumberException(userLogin.getPhoneNumber());
        }

        if (userLogin.getEmail() != null && !userLogin.getEmail().isBlank() &&
                !Validator.validateEmail(userLogin.getEmail())) {
            throw new InvalidEmailAddressException(userLogin.getEmail());
        }

        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                userLogin.getUserIdentity(),
                                userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser userDetails = (AppUser) authentication.getPrincipal();

        log.info("Token requested for user :{}", authentication.getAuthorities());
        log.info("User Details :{}", userDetails);

        String token = authService.generateToken(authentication);
        LoginResponseDTO response = new LoginResponseDTO("User logged in successfully", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO userRegisterRequest) {
        return ResponseEntity.ok(userService.saveUser(userRegisterRequest));
    }

}
