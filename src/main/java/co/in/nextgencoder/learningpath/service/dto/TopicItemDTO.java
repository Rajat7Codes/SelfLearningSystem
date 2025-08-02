package co.in.nextgencoder.learningpath.service.dto;

import co.in.nextgencoder.learningpath.domain.enumeration.Difficulty;
import co.in.nextgencoder.learningpath.domain.enumeration.TopicType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class TopicItemDTO {
    private Long id;
    private String title;
    private String description;
    private Integer alignment;
    private String reference;
    private String secondaryReference;
    private Difficulty difficulty;
    private TopicType topicType;
    private Long topicId;
}