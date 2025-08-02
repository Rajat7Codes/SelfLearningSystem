package co.in.nextgencoder.learningpath.reporitory;

import co.in.nextgencoder.learningpath.domain.AppUser;
import co.in.nextgencoder.learningpath.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Section entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

}