package site.qipeng.wxapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.qipeng.wxapi.dao.HistoryRepository;
import site.qipeng.wxapi.entity.History;
import site.qipeng.wxapi.service.HistoryService;

import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public History insert(History history) {
        history.setCreateTime(new Date());
        return historyRepository.save(history);
    }

    @Override
    public List<History> findByUserIdAndVideoId(Integer userId, Integer videoId) {
        return historyRepository.findByUserIdAndVideoId(userId, videoId);
    }

    @Override
    public List<History> findByUserId(Integer userId) {
        return historyRepository.findByUserId(userId);
    }
}
