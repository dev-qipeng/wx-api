package site.qipeng.wxapi.service;

import org.springframework.data.domain.Page;
import site.qipeng.wxapi.entity.Comment;
import site.qipeng.wxapi.entity.vo.CommentVO;

import java.util.List;

public interface CommentService {
    Page findByVideoId(Integer videoId, Integer pageNum, Integer pageSize);

    Comment insertVideo(Comment comment);

    List<CommentVO> findComment(Integer videoId);
}
