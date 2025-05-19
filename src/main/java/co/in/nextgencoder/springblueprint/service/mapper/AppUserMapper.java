package co.in.nextgencoder.springblueprint.service.mapper;

import co.in.nextgencoder.springblueprint.domain.AppUser;
import co.in.nextgencoder.springblueprint.service.dto.RegisterRequestDTO;
import co.in.nextgencoder.springblueprint.service.dto.RegisterResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    @Mapping(target = "username", expression = "java(" +
            "request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank() ? request.getPhoneNumber() : request.getEmail()" +
            ")")
    AppUser toAppUserDomain(RegisterRequestDTO request);

    RegisterResponseDTO toAppUserDto(AppUser appUser);
}
