package co.in.nextgencoder.learningpath.service.mapper;

import co.in.nextgencoder.learningpath.domain.Section;
import co.in.nextgencoder.learningpath.service.dto.SectionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    SectionDTO toSectionDTO(Section section);

    Section toSection(SectionDTO section);
}
