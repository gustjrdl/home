package tripboat.tripboat1.Mypage;


import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tripboat.tripboat1.CommentFile.Comment;
import tripboat.tripboat1.CommentFile.CommentService;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityService;
import tripboat.tripboat1.User.UserService;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final UserService userService;
    private final CommunityService communityService;

    private final CommentService commentService;
    private int[] categoryState = {0, 1, 2};

    @RequestMapping(value = {"","/content"})
    private String myPageForm(Model model, Principal principal, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Community> community = this.communityService.getIndexList(page,kw);

        model.addAttribute("kw", kw);
        model.addAttribute("name",userService.getUser(principal.getName()));
        model.addAttribute("paging", community);
        model.addAttribute("categoryState",categoryState[0]);

        return "MyPage";
    }

    @RequestMapping("/comment")
    private String myComment(Model model, Principal principal, @RequestParam(value="page", defaultValue="0") int page) {

        Page<Comment> comment = this.commentService.getCommentList(page);


        model.addAttribute("name",userService.getUser(principal.getName()));
        model.addAttribute("commentList", comment);
        model.addAttribute("categoryState",categoryState[1]);

        return "MyPage";
    }

    @RequestMapping("/profile")
    private String myUserData(Principal principal, Model model) {

        model.addAttribute("categoryState",categoryState[2]);
        model.addAttribute("myData",userService.getUser(principal.getName()));

        return "MyPage";
    }




}
