package co.in.nextgencoder.learningpath.service.mapper;

import co.in.nextgencoder.learningpath.domain.AppUser;
import co.in.nextgencoder.learningpath.service.dto.RegisterRequestDTO;
import co.in.nextgencoder.learningpath.service.dto.RegisterResponseDTO;
import co.in.nextgencoder.learningpath.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    @Mapping(target = "username", expression = "java(" +
            "request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank() ? request.getPhoneNumber() : request.getEmail()" +
            ")")
    AppUser toAppUserDomain(RegisterRequestDTO request);

    RegisterResponseDTO toAppUserDto(AppUser appUser);

    UserDTO toUserDTO(AppUser appUser);

    AppUser toAppUser(UserDTO userDTO);
}
