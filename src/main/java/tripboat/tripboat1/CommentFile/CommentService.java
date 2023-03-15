package tripboat.tripboat1.CommentFile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.User.SiteUser;
import tripboat.tripboat1.Util.DataNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(Community community, String content, SiteUser author) {
        Comment cmt = new Comment();
        cmt.setContent(content);
        cmt.setCreateDate(LocalDateTime.now());
        cmt.setCommunity(community);
        cmt.setAuthor(author);
        this.commentRepository.save(cmt);
    }

    public Page<Comment> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 5, Sort.by(sorts));

        return this.commentRepository.findAll(pageable);
    }




    public Comment getComment(Integer id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }

    public void modify(Comment comment, String content) {
        comment.setContent(content);
        comment.setModifyDate(LocalDateTime.now());
        this.commentRepository.save(comment);
    }

    public void delete(Comment comment) {

        this.commentRepository.delete(comment);
    }

}
