package tripboat.tripboat1.CommunityFile;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;
import tripboat.tripboat1.CommentFile.Comment;
import tripboat.tripboat1.User.SiteUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 3000)
    @NotNull
    private String content;

    private String subject;

    @Column(length = 20)
    private String region;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "community", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    private LocalDateTime modifyDate;

    @ManyToOne
    private SiteUser author;

    @Column(columnDefinition = "Integer default 0", nullable = false)
    private int view;
}