package tripboat.tripboat1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.test.context.SpringBootTest;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityForm;
import tripboat.tripboat1.CommunityFile.CommunityRepository;
import tripboat.tripboat1.CommunityFile.CommunityService;
import tripboat.tripboat1.User.SiteUser;

import java.time.LocalDateTime;

@SpringBootTest
class Tripboat1ApplicationTests {

	@Autowired
	private CommunityService communityService;

	private CommunityRepository communityRepository;
	private CommunityForm communityForm;
	private SiteUser siteUser;

	@Test
	void testJpa() {

	}

}
