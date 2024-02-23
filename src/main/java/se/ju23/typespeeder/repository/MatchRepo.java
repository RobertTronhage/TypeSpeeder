/**
 * Repository interface for managing Match data.
 *
 * @author Robert Tronhage
 */
package se.ju23.typespeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.entity.Match;

@Repository
public interface MatchRepo extends JpaRepository<Match,Long> {
}
