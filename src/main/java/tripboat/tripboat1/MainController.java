package tripboat.tripboat1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class MainController {
    @Autowired
    private final CommunityService communityService;
    @RequestMapping(value = {"/main",""})
    public String main (Model model,
                        @RequestParam(value="page", defaultValue="0") int page,
                        @RequestParam(value = "kw", defaultValue ="") String kw) {

        Page<Community> page1 = this.communityService.getIndexList(page, kw);

        Page<Community> paging = this.communityService.getList(page, kw);

        Page<Community> viewContent = this.communityService.getViewList(page, kw);

        model.addAttribute("kw", kw);
        model.addAttribute("paging", paging);
        model.addAttribute("newList",page1);
        model.addAttribute("viewContent",viewContent);





        return "Index";
    }




}
