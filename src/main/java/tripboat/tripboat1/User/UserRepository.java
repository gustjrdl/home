package tripboat.tripboat1.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.User.SiteUser;

import javax.persistence.criteria.Join;
import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByusername(String username);



}