package tripboat.tripboat1.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tripboat.tripboat1.CommentFile.Comment;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String Email;

    @Column(unique = true)
    private String nickname;

}