package co.in.nextgencoder.learningpath.service.mapper;

import co.in.nextgencoder.learningpath.domain.UserTopicProgress;
import co.in.nextgencoder.learningpath.service.dto.UserTopicProgressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserTopicProgressMapper {

    @Mapping(source = "topicItem.id", target = "topicItemId")
    UserTopicProgressDTO toUserTopicProgressDTO(UserTopicProgress userTopicProgress);

    @Mapping(source = "topicItemId", target = "topicItem.id")
    UserTopicProgress toUserTopicProgress(UserTopicProgressDTO userTopicProgress);
}
