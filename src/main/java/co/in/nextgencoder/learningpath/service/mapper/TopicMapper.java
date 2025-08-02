package co.in.nextgencoder.learningpath.service.mapper;

import co.in.nextgencoder.learningpath.domain.Topic;
import co.in.nextgencoder.learningpath.service.dto.TopicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    @Mapping(source = "division.id", target = "divisionId")
    TopicDTO toTopicDTO(Topic topic);

    @Mapping(source = "divisionId", target = "division.id")
    Topic toTopic(TopicDTO topic);
}
