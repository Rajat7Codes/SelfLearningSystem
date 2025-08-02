package co.in.nextgencoder.learningpath.service.mapper;

import co.in.nextgencoder.learningpath.domain.Division;
import co.in.nextgencoder.learningpath.service.dto.DivisionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DivisionMapper {

    @Mapping(source = "section.id", target = "sectionId")
    DivisionDTO toDivisionDTO(Division division);

    @Mapping(source = "sectionId", target = "section.id")
    Division toDivision(DivisionDTO division);
}
