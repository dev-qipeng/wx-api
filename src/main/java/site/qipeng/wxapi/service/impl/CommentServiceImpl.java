package site.qipeng.wxapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import site.qipeng.wxapi.dao.CommentRepository;
import site.qipeng.wxapi.service.CommentService;

import javax.persistence.criteria.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page findByVideoId(Integer videoId, Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize,Sort.Direction.DESC, "createTime");
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {

                Path<Integer> videoIdPath = root.get("videoId");
                final Predicate predicate = cb.equal(videoIdPath, videoId);
                return predicate;
            }
        }
        return commentRepository.findAll(spec, pageable);
    }
}
