package co.in.nextgencoder.learningpath.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class MinDivisionDTO {
    private Long id;
    private String title;
    private Integer alignment;
    private Long totalTopics = 0L;
    private Long completedTopics = 0L;
    private Boolean completed;
    private List<MinTopicDTO> topics;
}
