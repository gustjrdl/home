package tripboat.tripboat1.CommunityFile;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tripboat.tripboat1.CommentFile.Comment;
import tripboat.tripboat1.User.SiteUser;
import tripboat.tripboat1.Util.DataNotFoundException;


import javax.persistence.JoinColumn;
import javax.persistence.criteria.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommunityService {

    private final CommunityRepository communityRepository;


    public Page<Community> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 50, Sort.by(sorts));
        Specification<Community> spec = search(kw);

        return this.communityRepository.findAll(spec,pageable);
    }

    public void sangsung( String subject, String content){
        Community community = new Community();

        community.setSubject(subject);
        community.setContent(content);
        community.setModifyDate(LocalDateTime.now());
        this.communityRepository.save(community);
    }
    public Community create(CommunityForm communityForm,SiteUser nickname) {
        Community community = Community.builder()
                .subject(communityForm.getSubject())
                .content(communityForm.getContent())
                .region(communityForm.getRegion())
                .createDate(LocalDateTime.now())
                .author(nickname)
                .build();
        this.communityRepository.save(community);
        return community;
    }

    public void modify(Community community, String subject, String content, String region) {

        community.setSubject(subject);
        community.setContent(content);
        community.setRegion(region);
        community.setModifyDate(LocalDateTime.now());
        this.communityRepository.save(community);
    }

    public void delete(Community community) {
        this.communityRepository.delete(community);
    }

    public Community getCommunity(Integer id) {
        Optional<Community> community = this.communityRepository.findById(id);
        if (community.isPresent()) {
            Community community1= community.get();
            community1.setView(community.get().getView()+1);
            this.communityRepository.save(community1);
            return community1;
        } else {
            throw new DataNotFoundException("community not found");
        }
    }

    private Specification<Community> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Community> q, CriteriaQuery<?> query, CriteriaBuilder cb) {

                        query.distinct(true);  // 중복을 제거
                Join<Community, SiteUser> s1 = q.join("author", JoinType.LEFT);
                Join<Community, Comment> s2 = q.join("commentList", JoinType.LEFT);
                Join<Comment, SiteUser> s3 = s2.join("author", JoinType.LEFT);

                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(q.get("region"), "%" + kw + "%"),      // 지역
                        cb.like(s1.get("nickname"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(s2.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(s3.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }


}
