package co.in.nextgencoder.learningpath.reporitory;

import co.in.nextgencoder.learningpath.domain.Division;
import co.in.nextgencoder.learningpath.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Division entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

    List<Division> findAllBySectionIdOrderByAlignmentAsc(long sectionId);
}