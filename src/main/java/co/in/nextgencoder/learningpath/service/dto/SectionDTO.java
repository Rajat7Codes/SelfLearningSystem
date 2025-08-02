package co.in.nextgencoder.learningpath.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SectionDTO {
    private Long id;
    private String title;
    private String description;
    private Integer alignment;
}