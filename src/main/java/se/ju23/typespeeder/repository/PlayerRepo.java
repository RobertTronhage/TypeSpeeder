/**
 * Repository interface for managing Player data.
 *
 * @author Robert Tronhage
 */
package se.ju23.typespeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.entity.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player,Long> {

    Player getPlayerByUserNameAndPassword(String username, String password);
}
