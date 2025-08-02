package co.in.nextgencoder.learningpath.reporitory;

import co.in.nextgencoder.learningpath.domain.Section;
import co.in.nextgencoder.learningpath.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA repository for the Topic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByDeadlineDateLessThanEqual(LocalDate date);
}