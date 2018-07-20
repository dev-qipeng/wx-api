package site.qipeng.wxapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import site.qipeng.wxapi.common.util.JsonResult;
import site.qipeng.wxapi.common.util.JsonResultUtil;
import site.qipeng.wxapi.entity.Comment;
import site.qipeng.wxapi.entity.vo.CommentVO;
import site.qipeng.wxapi.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "list-by-page/{videoId}")
    public Page listByVideoId(@PathVariable(value = "videoId") Integer videoId,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){

        try{
            Page page = commentService.findByVideoId(videoId, pageNum, pageSize);
            return page;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "list/{videoId}")
    public List<CommentVO> listByVideoId(@PathVariable(value = "videoId") Integer videoId){

        try{
            List<CommentVO> comment = commentService.findComment(videoId);
            for (CommentVO c : comment) {
                System.out.println(c.toString());
            }
            return comment;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @PostMapping(value = "insert")
    public JsonResult insert(@RequestBody Comment comment){

        try{
            System.out.println(comment.toString());
            Comment result = commentService.insertVideo(comment);
            if (result == null){
                return JsonResultUtil.getErrorJson("添加评论失败");
            }
            return JsonResultUtil.getSuccessJson("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtil.getErrorJson("添加评论失败");
        }
    }
}
