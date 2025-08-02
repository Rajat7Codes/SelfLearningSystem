package co.in.nextgencoder.learningpath.domain;

import co.in.nextgencoder.learningpath.domain.enumeration.Difficulty;
import co.in.nextgencoder.learningpath.domain.enumeration.TopicType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * A TopicItem.
 */
@Entity
@Table(name = "topic_item")
@Getter
@Setter
@ToString
public class TopicItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "alignment")
    private Integer alignment;

    @Column(name = "reference")
    private String reference;

    @Column(name = "secondary_reference")
    private String secondaryReference;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TopicType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"topicItems", "division"}, allowSetters = true)
    private Topic topic;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TopicItem)) {
            return false;
        }
        return getId() != null && getId().equals(((TopicItem) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
