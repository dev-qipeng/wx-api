package site.qipeng.wxapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import site.qipeng.wxapi.common.util.JsonResult;
import site.qipeng.wxapi.common.util.JsonResultUtil;
import site.qipeng.wxapi.entity.User;
import site.qipeng.wxapi.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public JsonResult login(@RequestParam("authCode") String authCode,
                            @RequestParam("encryptedData") String encryptedData,
                            @RequestParam("iv") String iv,
                            User userInfo,
                            HttpServletResponse response){

        Map<String, Object> map = userService.wxLogin(authCode, encryptedData, iv);
        if(null == map || StringUtils.isEmpty(map.get("openid"))){
            return JsonResultUtil.getErrorJson("参数错误");
        }
        String openId = (String)map.get("openid");
        User user = userService.findByOpenId(openId);
        if(user == null){
            user.setOpenId(openId);
            user.setToken(DigestUtils.md5DigestAsHex(openId.getBytes()));
            user.setCreateTime(new Date());
            user = userService.save(user);
        }
        userService.setAuthToken(response, user.getToken());

        return JsonResultUtil.getObjectJson(userInfo);
    }

}
