package site.qipeng.wxapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import site.qipeng.wxapi.QipengProperties;
import site.qipeng.wxapi.common.util.JsonResultUtil;
import site.qipeng.wxapi.dao.BannerRepository;
import site.qipeng.wxapi.entity.Banner;
import site.qipeng.wxapi.entity.Collection;
import site.qipeng.wxapi.entity.Video;
import site.qipeng.wxapi.entity.vo.VideoVO;
import site.qipeng.wxapi.service.CollectionService;
import site.qipeng.wxapi.service.VideoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("video")
public class VideoController {

    @Autowired
    private QipengProperties qipengProperties;

    @Autowired
    private VideoService videoService;

    @Autowired
    CollectionService collectionService;

    public static Integer pageSize = 5;

    @GetMapping(value = "list")
    public Page<Video> getAll(@RequestParam(value = "categoryId", required = false, defaultValue = "") Integer categoryId,
                              @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        Map<String, Object> param = new HashMap<>();
        param.put("categoryId",categoryId);
        Page<Video> videoPage = videoService.findAll(param, pageNum, pageSize);
        return videoPage;
    }

//    @GetMapping(value = "detail/{id}")
//    public Video getOne(@PathVariable(value = "id") Integer id){
//        Video video = videoService.findById(id);
//        return video;
//    }
    @GetMapping(value = "detail/{id}")
    public VideoVO getOne(@PathVariable(value = "id") Integer id, Integer userId){

        VideoVO vo = null;

        Video video = videoService.findById(id);
        vo = new VideoVO(video);

        List<Collection> collectionList = collectionService.findByUserIdAndVideoId(userId, id);
        if(collectionList != null && collectionList.size() > 0){
            vo.setLike(true);
        }else {
            vo.setLike(false);
        }

        return vo;
    }


    @GetMapping(value = "banner-list")
    public List<Banner> list(){
        return videoService.findBannerAll();
    }

    @GetMapping(value = "search")
    public Page<Video> search(@RequestParam(value = "keyword", required = true) String keyword,
                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        Map<String, Object> param = new HashMap<>();
        param.put("keyword",keyword);
        Page<Video> videoPage = videoService.search(param, pageNum, pageSize);
        return videoPage;
    }

}
