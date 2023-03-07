package tripboat.tripboat1.User;


import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityService;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    private final CommunityService communityService;


    @RequestMapping("/mypage")
    private String mypageform(Model model, Principal principal, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Community> community = this.communityService.getList(page,kw);

        model.addAttribute("name",userService.getUser(principal.getName()));
        model.addAttribute("community", community);

        return "MyPage";
    }

    @GetMapping("/user/{id}")
    private String seeUser(Model model,@RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Community> communityPage = this.communityService.getList(page, kw);

        model.addAttribute("community", communityPage);
        return "UserPage";
    }

    @PostMapping("/user/{id}")
    private String userPage(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value="userNickname") String userData,
                            @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Community> communityPage = this.communityService.getList(page, kw);

        model.addAttribute("userData", userData);
        model.addAttribute("community", communityPage);
        return "UserPage";
    }

}
