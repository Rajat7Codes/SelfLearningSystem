package co.in.nextgencoder.learningpath.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class TopicDTO {
    private Long id;
    private String title;
    private String description;
    private Integer alignment;
    private LocalDate deadlineDate;
    private Long divisionId;
}