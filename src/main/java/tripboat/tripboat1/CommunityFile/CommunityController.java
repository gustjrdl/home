package tripboat.tripboat1.CommunityFile;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tripboat.tripboat1.CommentFile.CommentForm;
import tripboat.tripboat1.User.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private final CommunityService communityService;
    private final UserService userService;

    @RequestMapping("")
    private String list(Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Community> paging = this.communityService.getList(page, kw);

        model.addAttribute("kw", kw);
        model.addAttribute("paging", paging);
        return "CommunityMain";
    }
    @GetMapping("")
    private String community(Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Community> paging = this.communityService.getList(page, kw);

        model.addAttribute("kw", kw);
        model.addAttribute("paging", paging);

        return "CommunityMain";
    }

    @GetMapping("/detail/{id}")
    private String detail(Model model, @PathVariable("id") Integer id, CommentForm commentForm,  @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {

        Community community = this.communityService.getCommunity(id);
        Page<Community> paging = this.communityService.getList(page, kw);

        model.addAttribute("community", community);
        model.addAttribute("kw", kw);
        model.addAttribute("paging", paging);

        return "CommunityContent";
    }


}