package tripboat.tripboat1.CommunityFile;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import tripboat.tripboat1.User.SiteUser;

import javax.persistence.Id;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    Page<Community> findAll(Specification<Community> spec, org.springframework.data.domain.Pageable pageable);
}
