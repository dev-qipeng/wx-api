package site.qipeng.wxapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.qipeng.wxapi.entity.Collection;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    List<Collection> findByUserIdAndVideoId(Integer userId, Integer videoId);
    List<Collection> findByUserIdOrderByCreateTimeDesc(Integer userId);
}
