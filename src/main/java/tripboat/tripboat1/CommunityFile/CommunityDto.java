package tripboat.tripboat1.CommunityFile;

import lombok.Getter;
import lombok.Setter;
import tripboat.tripboat1.CommentFile.Comment;
//import tripboat.tripboat1.CommunityFile.CommunityImg.ArticleImageDto;
import tripboat.tripboat1.User.SiteUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommunityDto {

    private Integer id;

    private String nickname;

    private String subject;

    private List<Comment> commentList;
    private String content;

    private SiteUser author;

    private LocalDateTime createDate;

    private LocalDateTime updatedDate;

    private Boolean isTemp;
    private Integer view;


    public CommunityDto(Community community) {

        this.id = community.getId();

        this.subject = community.getSubject();

        this.content = community.getContent();

        this.commentList = community.getCommentList();

        this.createDate = community.getCreateDate();

        this.view = community.getView();

    }

}
