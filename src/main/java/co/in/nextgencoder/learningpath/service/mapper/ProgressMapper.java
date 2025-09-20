package co.in.nextgencoder.learningpath.service.mapper;

import co.in.nextgencoder.learningpath.domain.Division;
import co.in.nextgencoder.learningpath.domain.Topic;
import co.in.nextgencoder.learningpath.domain.TopicItem;
import co.in.nextgencoder.learningpath.service.dto.MinDivisionDTO;
import co.in.nextgencoder.learningpath.service.dto.MinTopicDTO;
import co.in.nextgencoder.learningpath.service.dto.ProgressTopicItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgressMapper {

    MinDivisionDTO toMinDivisionDTO(Division division);

    MinTopicDTO toMinTopicDTO(Topic topic);

    ProgressTopicItemDTO toProgressTopicItemDTO(TopicItem topicItem);
}
