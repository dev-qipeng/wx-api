package site.qipeng.wxapi.service;

import site.qipeng.wxapi.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserService {
    Map<String,Object> wxLogin(String authCode, String encryptedData, String iv);

    User save(User user);

    User findByOpenId(String openId);

    void setAuthToken(HttpServletResponse response, String token);
}
