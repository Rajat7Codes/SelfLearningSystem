package co.in.nextgencoder.learningpath.service;

import co.in.nextgencoder.learningpath.domain.AppUser;
import co.in.nextgencoder.learningpath.reporitory.AppUserRepository;
import co.in.nextgencoder.learningpath.service.dto.RegisterRequestDTO;
import co.in.nextgencoder.learningpath.service.dto.RegisterResponseDTO;
import co.in.nextgencoder.learningpath.service.dto.UserDTO;
import co.in.nextgencoder.learningpath.service.exception.BadRequestException;
import co.in.nextgencoder.learningpath.service.exception.validation.InvalidEmailAddressException;
import co.in.nextgencoder.learningpath.service.exception.validation.InvalidPhoneNumberException;
import co.in.nextgencoder.learningpath.service.mapper.AppUserMapper;
import co.in.nextgencoder.learningpath.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserMapper appUserMapper;
    private BadRequestException userIsNotAuthenticated;

    @Override
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {
        return appUserRepository
                .findByEmailOrPhoneNumber(identity)
                .orElseThrow(() -> new UsernameNotFoundException("Identity not found: " + identity));
    }

    public RegisterResponseDTO saveUser(RegisterRequestDTO registerRequestDTO) {
        if (registerRequestDTO.getPhoneNumber() != null && !registerRequestDTO.getPhoneNumber().isBlank() &&
                !Validator.validatePhoneNumber(registerRequestDTO.getPhoneNumber())) {
            throw new InvalidPhoneNumberException(registerRequestDTO.getPhoneNumber());
        }

        if (registerRequestDTO.getEmail() != null && !registerRequestDTO.getEmail().isBlank() &&
                !Validator.validateEmail(registerRequestDTO.getEmail())) {
            throw new InvalidEmailAddressException(registerRequestDTO.getEmail());
        }

        registerRequestDTO.setPassword(new BCryptPasswordEncoder().encode(registerRequestDTO.getPassword()));
        AppUser appUser = appUserMapper.toAppUserDomain(registerRequestDTO);
        AppUser result = appUserRepository.save(appUser);

        return appUserMapper.toAppUserDto(result);
    }

    public UserDTO getUserProfile() {
        return appUserMapper.toUserDTO(getCurrentLoggedInUser());
    }

    private AppUser getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) throw new BadRequestException("User is not logged in.");
        return appUserRepository.findByEmailOrPhoneNumber(authentication.getName())
                .orElseThrow(() -> new BadRequestException("User not found")
                );
    }
}