package tripboat.tripboat1.CommentFile;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityService;
import tripboat.tripboat1.User.SiteUser;
import tripboat.tripboat1.User.UserService;
import tripboat.tripboat1.Util.DataNotFoundException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommunityService communityService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id,
                                @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw, @RequestParam String content, Principal principal, @Valid CommentForm commentForm, BindingResult bindingResult ) {

        Community community = this.communityService.getCommunity(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        Page<Community> paging = this.communityService.getList(page, kw);

        model.addAttribute("kw", kw);
        model.addAttribute("paging", paging);

        if(bindingResult.hasErrors())
        {
            model.addAttribute("community", community);
            return "CommunityContent";
        }

        this.commentService.create(community, content, siteUser);
        return String.format("redirect:/community/detail/%s", id);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String commentModify(CommentForm commentForm, @PathVariable("id") Integer id, @RequestParam(value="page", defaultValue="0") int page, Principal principal) {

        List<Comment> comment = this.commentService.getComment(commentForm, id);
        return "CommentEdit";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String commentModify(@Valid CommentForm commentForm, BindingResult bindingResult,
                               @PathVariable("id") Integer id, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "CommentEdit";
        }

        List<Comment> comment = this.commentService.getComment(commentForm,id);

        if (!comment.get(id).getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.commentService.modify(comment, commentForm.getContent(),id);

        return String.format("redirect:/community/detail/%s", comment.get(id).getCommunity().getId());
    }

//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/delete/{id}")
//    public String answerDelete(Principal principal, @PathVariable("id") Integer id, @RequestParam(value="page", defaultValue = "0") int page) {
//
//        Page<Comment> comment = this.commentService.getList(page, id);
//
//
//        if (!comment.getContent().get(id).getAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//        }
//        this.commentService.delete();
//        return String.format("redirect:/community/detail/%s", comment.get().getCommunity().getId());
//    }

}
