package site.qipeng.wxapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.qipeng.wxapi.common.util.JsonResult;
import site.qipeng.wxapi.common.util.JsonResultUtil;
import site.qipeng.wxapi.entity.Collection;
import site.qipeng.wxapi.entity.Video;
import site.qipeng.wxapi.service.CollectionService;
import site.qipeng.wxapi.service.VideoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("collection")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    VideoService videoService;

    @PostMapping(value = "insert")
    public JsonResult insert(@RequestBody Collection collection){
        try{
            List<Collection> collectionList = collectionService.findByUserIdAndVideoId(collection.getUserId(), collection.getVideoId());
            if(collectionList != null && collectionList.size() > 0){
                return JsonResultUtil.getErrorJson("您已添加收藏");
            }
            Collection result = collectionService.insert(collection);
            if (result == null){
                return JsonResultUtil.getErrorJson("收藏失败");
            }
            return JsonResultUtil.getSuccessJson("收藏成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtil.getErrorJson("收藏失败");
        }

    }

    @PostMapping(value = "delete")
    public JsonResult delete(@RequestBody Collection collection){
        try{

            List<Collection> collectionList = collectionService.findByUserIdAndVideoId(collection.getUserId(), collection.getVideoId());
            if(collectionList == null || collectionList.size() == 0){
                return JsonResultUtil.getErrorJson("您未收藏此视频");
            }
            Collection record = collectionList.get(0);

            collectionService.delete(record);

            return JsonResultUtil.getSuccessJson("取消收藏成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtil.getErrorJson("取消收藏失败");
        }
    }

    @GetMapping(value = "list/{userId}")
    public List<Video> listByVideoId(@PathVariable(value = "userId") Integer userId){

        try{
            List<Collection> list = collectionService.findCommentByUserId(userId);
            if (list == null || list.size() == 0){
                return new ArrayList<>();
            }
            List<Integer> videoIds = new ArrayList<>();
            for (Collection c : list) {
                videoIds.add(c.getVideoId());
            }
            List<Video> videoList = videoService.findByVideoIdIn(videoIds);

            return videoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
