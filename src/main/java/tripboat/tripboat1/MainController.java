package tripboat.tripboat1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityService;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class MainController {
//    @Autowired
//    private final CommunityService communityService;
    @RequestMapping("/main")
    public String main (Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
//        Page<Community> paging = this.communityService.getList(page, kw);
//        model.addAttribute("kw", kw);
//        model.addAttribute("paging", paging);
        return "Index";
    }




}
