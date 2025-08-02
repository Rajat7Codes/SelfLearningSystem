package co.in.nextgencoder.learningpath.service.dto;

import co.in.nextgencoder.learningpath.domain.enumeration.ProgressStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserTopicProgressDTO {
    private Long id;
    private Long topicItemId;
    private ProgressStatus status;
}
