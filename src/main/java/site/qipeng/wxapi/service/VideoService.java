package site.qipeng.wxapi.service;

import org.springframework.data.domain.Page;
import site.qipeng.wxapi.entity.Banner;
import site.qipeng.wxapi.entity.Video;

import java.util.List;
import java.util.Map;

public interface VideoService {
    Page<Video> findAll(Map<String, Object> param, Integer pageNum, Integer pageSize);

    Video findById(Integer id);

    List<Banner> findBannerAll();

    Page<Video> search(Map<String,Object> param, Integer pageNum, Integer pageSize);
}
