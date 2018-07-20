package site.qipeng.wxapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.qipeng.wxapi.dao.CollectionRepository;
import site.qipeng.wxapi.entity.Collection;
import site.qipeng.wxapi.service.CollectionService;

import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionRepository collectionRepository;

    @Override
    public Collection insert(Collection collection) {
        collection.setCreateTime(new Date());
        Collection save = (Collection)collectionRepository.save(collection);
        return save;
    }

    @Override
    public List<Collection> findByUserIdAndVideoId(Integer userId, Integer videoId) {
        System.out.println(userId+"---"+videoId);
        return collectionRepository.findByUserIdAndVideoId(userId, videoId);
    }

    @Override
    public void delete(Collection record) {
        collectionRepository.delete(record);
    }

    @Override
    public List<Collection> findCommentByUserId(Integer userId) {
        return collectionRepository.findByUserIdOrderByCreateTimeDesc(userId);
    }
}
