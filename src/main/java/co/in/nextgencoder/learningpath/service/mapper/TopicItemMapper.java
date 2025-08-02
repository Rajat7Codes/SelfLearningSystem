package co.in.nextgencoder.learningpath.service.mapper;

import co.in.nextgencoder.learningpath.domain.TopicItem;
import co.in.nextgencoder.learningpath.service.dto.ActiveTopicItemDTO;
import co.in.nextgencoder.learningpath.service.dto.TopicItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TopicItemMapper {

    @Mapping(source = "topic.id", target = "topicId")
    TopicItemDTO toTopicItemDTO(TopicItem topicItem);

    @Mapping(source = "topicId", target = "topic.id")
    TopicItem toTopicItem(TopicItemDTO topicItemDTO);

    ActiveTopicItemDTO toActiveTopicItemDTO(TopicItem topicItem);
}
