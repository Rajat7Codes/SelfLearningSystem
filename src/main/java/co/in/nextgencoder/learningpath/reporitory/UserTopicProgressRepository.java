package co.in.nextgencoder.learningpath.reporitory;

import co.in.nextgencoder.learningpath.domain.TopicItem;
import co.in.nextgencoder.learningpath.domain.UserTopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the UserTopicProgress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserTopicProgressRepository extends JpaRepository<UserTopicProgress, Long> {

    Optional<UserTopicProgress> findByUserIdAndTopicItemId(Long id, Long id1);
}