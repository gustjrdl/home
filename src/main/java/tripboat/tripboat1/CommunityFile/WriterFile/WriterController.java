package tripboat.tripboat1.CommunityFile.WriterFile;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.server.ResponseStatusException;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityForm;
import tripboat.tripboat1.CommunityFile.CommunityService;
import tripboat.tripboat1.User.SiteUser;
import tripboat.tripboat1.User.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.FileStore;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private final CommunityService communityService;
    private final UserService userService;
//    private final ArticleImageService articleImageService;
//    private final AwsService awsService;

    @RequestMapping("/content")
    private String WriterSearch(Model model, @RequestParam("file") List<MultipartFile> file, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Community> paging = this.communityService.getList(page, kw);
        model.addAttribute("kw", kw);
        model.addAttribute("paging", paging);
        return "Writer";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/content")
    public String WriterForm (CommunityForm communityForm){
        return "Writer";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/content")
    public String WriterDate (Model model, @Valid CommunityForm communityForm, BindingResult bindingResult, Principal principal,
                              @RequestParam(value="files", required = false) List<MultipartFile> files) throws IOException {

        Community article = communityService.create(communityForm,userService.getUser(principal.getName()));
        if (bindingResult.hasErrors()) return "Writer";

//        if(!files.isEmpty()) {
//            files.stream()
//                    .forEach(file -> {
//                        try {
//                            int checkNum = 1;
//
//                            if (file.isEmpty()) checkNum = 0;
//
//                            if (checkNum == 1) {
////                                String imgUrl = awsService.sendFileToS3Bucket(file);
////                                model.addAttribute("fileUrl", imgUrl);
////                                articleImageService.articleImageDto(imgUrl, article);
//                            }
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//        }
//        org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@30300607
//        org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@3faafd59

//        System.out.println("files"+files);
//        System.out.println("files2"+files.isEmpty());

        return "redirect:/community";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String communityModify(CommunityForm communityForm, @PathVariable("id") Integer id, Principal principal) {
        Community community = this.communityService.getCommunity(id);
        if(!community.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        communityForm.setSubject(community.getSubject());
        communityForm.setContent(community.getContent());
        communityForm.setRegion(community.getRegion());
        return "Writer";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String communityMoidify(@Valid CommunityForm communityForm, BindingResult bindingResult,
                                   Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "CommunityMain";
        }
        Community community = this.communityService.getCommunity(id);

        if (!community.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.communityService.modify(community, communityForm.getSubject(), communityForm.getContent(),communityForm.getRegion());

        return String.format("redirect:/community/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String communitydelete(Principal principal, @PathVariable("id") Integer id) {
        Community community = this.communityService.getCommunity(id);

        if (!community.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        this.communityService.delete(community);
        return "redirect:/community";
    }

}