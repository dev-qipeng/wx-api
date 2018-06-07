package site.qipeng.wxapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import site.qipeng.wxapi.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Integer>, PagingAndSortingRepository<Video, Integer>, JpaSpecificationExecutor<Video> {
    
}
