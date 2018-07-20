package site.qipeng.wxapi.service;


import site.qipeng.wxapi.entity.Collection;

import java.util.List;

public interface CollectionService {
    Collection insert(Collection collection);

    List<Collection> findByUserIdAndVideoId(Integer userId, Integer videoId);

    void delete(Collection record);

    List<Collection> findCommentByUserId(Integer userId);
}
