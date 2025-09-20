package co.in.nextgencoder.learningpath.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class MinTopicDTO {
    private Long id;
    private String title;
    private Integer alignment;
    private Long totalTopicItems = 0L;
    private Long completedTopicItems = 0L;
    private Boolean completed;
    private List<ProgressTopicItemDTO> topicItems;
}


