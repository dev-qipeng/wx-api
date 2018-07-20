package site.qipeng.wxapi.service;

import site.qipeng.wxapi.entity.History;

import java.util.List;

public interface HistoryService {
    History insert(History history);

    List<History> findByUserIdAndVideoId(Integer userId, Integer videoId);

    List<History> findByUserId(Integer userId);
}
