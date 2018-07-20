package site.qipeng.wxapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.qipeng.wxapi.common.util.JsonResult;
import site.qipeng.wxapi.common.util.JsonResultUtil;
import site.qipeng.wxapi.entity.Collection;
import site.qipeng.wxapi.entity.History;
import site.qipeng.wxapi.entity.Video;
import site.qipeng.wxapi.service.HistoryService;
import site.qipeng.wxapi.service.VideoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("history")
public class HistoryController {

    @Autowired
    HistoryService historyService;


    @Autowired
    VideoService videoService;

    @PostMapping(value = "insert")
    public JsonResult insert(@RequestBody History history){
        System.out.println(history.toString());
        try{

            List<History> historyList = historyService.findByUserIdAndVideoId(history.getUserId(), history.getVideoId());
            if(historyList != null && historyList.size() > 0){
                return JsonResultUtil.getErrorJson("已浏览过此视频");
            }

            History result = historyService.insert(history);
            if (result == null){
                return JsonResultUtil.getErrorJson("添加浏览历史失败");
            }
            return JsonResultUtil.getSuccessJson("添加浏览历史成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtil.getErrorJson("添加浏览历史失败");
        }
    }

    @GetMapping(value = "list/{userId}")
    public List<Video> listByVideoId(@PathVariable(value = "userId") Integer userId){

        try{
            List<History> list = historyService.findByUserId(userId);
            if (list == null || list.size() == 0){
                return new ArrayList<>();
            }
            List<Integer> videoIds = new ArrayList<>();
            for (History c : list) {
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
