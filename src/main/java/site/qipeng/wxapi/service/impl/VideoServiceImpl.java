package site.qipeng.wxapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import site.qipeng.wxapi.dao.BannerRepository;
import site.qipeng.wxapi.dao.VideoRepository;
import site.qipeng.wxapi.entity.Banner;
import site.qipeng.wxapi.entity.Video;
import site.qipeng.wxapi.service.VideoService;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Video findById(Integer id) {
        Optional<Video> byId = videoRepository.findById(id);
        return byId.get();
    }

    @Override
    public Page<Video> findAll(Map<String, Object> param, Integer pageNum, Integer pageSize) {
        // 说明：Pageable排序所需的参数应该是Bean中的名字，如应该是createTime，而不是create_time
        Pageable pageable = new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "playNum");
        if (param == null || StringUtils.isEmpty(param.get("categoryId"))){
            return videoRepository.findAll(pageable);
        }
        Specification<Video> spec = new Specification<Video>() {

            @Override
            public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Integer> categoryId = root.get("categoryId");
                Predicate predicate = cb.and(cb.equal(categoryId, param.get("categoryId")));
                return predicate;
            }
        };

        return videoRepository.findAll(spec,pageable);
    }

    @Override
    public List<Banner> findBannerAll() {
        return bannerRepository.findAll();
    }

    @Override
    public Page<Video> search(Map<String, Object> param, Integer pageNum, Integer pageSize) {
        // 说明：Pageable排序所需的参数应该是Bean中的名字，如应该是createTime，而不是create_time
        Pageable pageable = new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "playNum");
        if (param == null || StringUtils.isEmpty(param.get("keyword"))){
            return videoRepository.findAll(pageable);
        }
        Specification<Video> spec = new Specification<Video>() {

            @Override
            public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> name = root.get("name");
                Predicate predicate = cb.like(name, "%"+param.get("keyword")+"%");
                return predicate;
            }
        };

        return videoRepository.findAll(spec,pageable);
    }

    @Override
    public List<Video> findByVideoIdIn(List<Integer> videoIds) {
        return videoRepository.findByIdIn(videoIds);
    }
}
