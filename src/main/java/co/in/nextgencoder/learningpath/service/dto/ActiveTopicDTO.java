package co.in.nextgencoder.learningpath.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
public class ActiveTopicDTO {
    private Long id;
    private String title;
    private String sectionTitle;
    private String divisionTitle;
    private LocalDate deadlineDate;
    private List<ActiveTopicItemDTO> topicItemList;
}
