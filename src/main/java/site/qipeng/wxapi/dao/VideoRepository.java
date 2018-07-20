package site.qipeng.wxapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import site.qipeng.wxapi.entity.Video;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer>, PagingAndSortingRepository<Video, Integer>, JpaSpecificationExecutor<Video> {

    List<Video> findByIdIn(List<Integer> videoIds);
}
