package tripboat.tripboat1.SearchController;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityService;

import java.security.Principal;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {


    @Autowired
    private final CommunityService communityService;

    @GetMapping("")
    public String search(@RequestParam(value = "kw", defaultValue = "") String kw, @RequestParam(value="page", defaultValue="0") int page, Model model) {
        Page<Community> searchResult = communityService.getList(page, kw);

        if (searchResult.isEmpty()) {
            model.addAttribute("message", "검색 결과가 없습니다.");
        } else {
            model.addAttribute("searchResult", searchResult);
        }

        return "CommunityMain";
    }


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

    @RequestMapping("")
    private String handleRequest(Model model, Principal principal, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        // 공통으로 수행할 로직

        return "Index"; // 반환할 뷰 이름
    }

    @RequestMapping("/board")
    private String handleBoardRequest(Model model, Principal principal, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        // 게시판 관련 로직

        // 공통 로직 호출
        return handleRequest(model, principal, page, kw);
    }

    @RequestMapping("/other")
    private String handleOtherRequest(Model model, Principal principal, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        // 다른 처리 로직

        // 공통 로직 호출
        return handleRequest(model, principal, page, kw);
    }
}
