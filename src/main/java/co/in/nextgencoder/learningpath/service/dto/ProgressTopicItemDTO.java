package co.in.nextgencoder.learningpath.service.dto;

import co.in.nextgencoder.learningpath.domain.enumeration.Difficulty;
import co.in.nextgencoder.learningpath.domain.enumeration.TopicType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProgressTopicItemDTO {
    private Long id;
    private String title;
    private Integer alignment;
    private String reference;
    private String secondaryReference;
    private Difficulty difficulty;
    private TopicType type;
    private Boolean completed;
}


