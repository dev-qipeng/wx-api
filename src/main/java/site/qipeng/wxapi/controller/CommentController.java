package site.qipeng.wxapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import site.qipeng.wxapi.common.util.JsonResult;
import site.qipeng.wxapi.entity.Comment;
import site.qipeng.wxapi.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "list/{videoId}")
    public Page listByVideoId(@PathVariable(value = "videoId") Integer videoId,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){

        try{
            Page page = commentService.findByVideoId(videoId, pageNum, pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
