package co.in.nextgencoder.learningpath.reporitory;

import co.in.nextgencoder.learningpath.domain.TopicItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the TopicItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicItemRepository extends JpaRepository<TopicItem, Long> {

    List<TopicItem> findAllByTopicIdOrderByAlignmentAsc(Long id);
}