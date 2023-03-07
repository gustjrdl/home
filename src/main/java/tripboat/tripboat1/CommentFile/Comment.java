package tripboat.tripboat1.CommentFile;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.User.SiteUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Community community;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;



}
