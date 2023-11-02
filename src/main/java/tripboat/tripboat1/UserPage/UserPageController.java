package tripboat.tripboat1.UserPage;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private final CommunityService communityService;

    @GetMapping("/{id}")
    private String seeUser(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Community> communityPage = this.communityService.getList(page, kw);

        model.addAttribute("community", communityPage);
        return "UserPage";
    }


    @PostMapping("/{id}")
    private String userPage(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value="userNickname") String userData,
                            @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Community> communityPage = this.communityService.getList(page, kw);

        model.addAttribute("userData", userData);
        model.addAttribute("community", communityPage);
        return "UserPage";
    }
}
