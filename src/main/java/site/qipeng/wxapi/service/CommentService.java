package site.qipeng.wxapi.service;

import org.springframework.data.domain.Page;

public interface CommentService {
    Page findByVideoId(Integer videoId, Integer pageNum, Integer pageSize);
}
