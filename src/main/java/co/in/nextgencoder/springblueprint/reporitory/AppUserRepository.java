package co.in.nextgencoder.springblueprint.reporitory;

import co.in.nextgencoder.springblueprint.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the AppUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Query("FROM AppUser a WHERE " +
            "(a.email is not null AND a.email=:identity) OR " +
            "(a.phoneNumber is not null AND a.phoneNumber=:identity)")
    Optional<AppUser> findByEmailOrPhoneNumber(String identity);
}