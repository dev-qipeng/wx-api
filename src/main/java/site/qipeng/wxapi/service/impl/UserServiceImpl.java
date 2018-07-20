package site.qipeng.wxapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import site.qipeng.wxapi.common.wx.WechatAPP;
import site.qipeng.wxapi.common.wx.WxUtils;
import site.qipeng.wxapi.dao.UserRepository;
import site.qipeng.wxapi.entity.User;
import site.qipeng.wxapi.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WechatAPP wechatAPP;

    @Override
    public Map<String, Object> wxLogin(String code, String encryptedData, String iv) {
        try {
            if (StringUtils.isEmpty(code)) {
                return null;
            }
            String requestUrl = wechatAPP.getUrl();
            Map<String, String> requestUrlParam = new HashMap<String,String>();
            requestUrlParam.put("appid", wechatAPP.getAppid());  //开发者设置中的appId
            requestUrlParam.put("secret", wechatAPP.getSECRET()); //开发者设置中的appSecret
            requestUrlParam.put("js_code", code); //小程序调用wx.login返回的code
            requestUrlParam.put("grant_type", "authorization_code");    //默认参数
            String responseStr = WxUtils.sendPost(requestUrl, requestUrlParam);

            // System.out.println(responseStr); // 输出示例：{"session_key":"yA9mbIBSPOnIBJlDfBbk4A==","expires_in":7200,"openid":"oSV4e0ewsNQhTem4OJJRyv53wmgo"}
            ObjectMapper mapper = new ObjectMapper();
            Map resMap = mapper.readValue(responseStr, Map.class);
            if(StringUtils.isEmpty(resMap.get("openid"))||StringUtils.isEmpty(resMap.get("session_key"))){
                return null;
            }
            String sessionKey = (String)resMap.get("session_key");

            // 此处报空指针异常，暂不作处理
//            String decrypt = WxUtils.decrypt(encryptedData, sessionKey, iv);
//            if (!StringUtils.isEmpty(decrypt)) {
//                resMap.putAll(mapper.readValue(decrypt, Map.class));
//            }
            return resMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByOpenId(String openId) {
        return userRepository.findByOpenId(openId);
    }

    @Override
    public void setAuthToken(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(365*24*60*60);
        response.addCookie(cookie);
    }
}
