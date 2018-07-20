package site.qipeng.wxapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import site.qipeng.wxapi.dao.CommentRepository;
import site.qipeng.wxapi.entity.Comment;
import site.qipeng.wxapi.entity.vo.CommentVO;
import site.qipeng.wxapi.service.CommentService;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page findByVideoId(Integer videoId, Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize,Sort.Direction.DESC, "createTime");
        Specification<Comment> spec = new Specification<Comment>(){

            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.equal(root.get("videoId").as(Integer.class), videoId);
                return predicate;
            }
        };
        return commentRepository.findAll(spec, pageable);
    }

    @Override
    public Comment insertVideo(Comment comment) {
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentVO> findComment(Integer videoId) {
        List<CommentVO> comment = commentRepository.findComment(videoId);
        return comment;
    }
}
