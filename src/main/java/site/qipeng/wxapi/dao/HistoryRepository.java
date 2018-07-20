package site.qipeng.wxapi.dao;

import site.qipeng.wxapi.common.BaseRepository;
import site.qipeng.wxapi.entity.History;

import java.util.List;

public interface HistoryRepository extends BaseRepository<History, Integer> {
    List<History> findByUserIdAndVideoId(Integer userId, Integer videoId);

    List<History> findByUserId(Integer userId);
}
