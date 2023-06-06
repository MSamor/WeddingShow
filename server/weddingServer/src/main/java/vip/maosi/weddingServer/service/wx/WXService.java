package vip.maosi.weddingServer.service.wx;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import vip.maosi.weddingServer.domain.User;
import vip.maosi.weddingServer.dto.WXUserInfoDto;
import vip.maosi.weddingServer.http.HttpClient;
import vip.maosi.weddingServer.meta.TokenProp;
import vip.maosi.weddingServer.service.UserService;

import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class WXService {
    @Autowired
    TokenProp tokenProp;
    @Autowired
    UserService userService;

    public synchronized String getOpenid(String code) {
        final var url = "https://api.weixin.qq.com/sns/jscode2session";
        final var params = Map.of(
                "appid", tokenProp.wechat.miniProgram.appId,
                "secret", tokenProp.wechat.miniProgram.appSecret,
                "js_code", code,
                "grant_type", "authorization_code"
        );
        String openid = null;
        try {
            final String response = HttpClient.get().get(url, params);
            if (response != null) {
                final var jo = new JSONObject(response);
                if (!jo.has("errcode") || jo.getInt("errcode") == 0) {
                    openid = jo.optString("openid");
                }
            }
        } catch (Exception e) {
            log.error("微信获取openid失败", e);
        }
        log.info("微信获取openid: " + openid);
        return openid;
    }

    public String silentLogin(String code) {
        if (StringUtils.isEmpty(code)) return null;
        val openid = getOpenid(code);
        final User user = getUser(openid);
        if (user == null) {
            val newUser = new User()
                    .setLastLoginDate(new Date())
                    .setSignDate(new Date())
                    .setOpenid(openid);
            val save = userService.save(newUser);
            if (save) {
                return openid;
            }
            return null;
        }
        user.setLastLoginDate(new Date());
        val update = userService.updateById(user);
        if (update) {
            return openid;
        }
        return null;
    }

    public User getUser(String openid) {
        return userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getOpenid, openid));
    }

    public boolean login(String openid, WXUserInfoDto userInfo) {
        val user = getUser(openid);
        if (user == null) return false;
        user.setLastLoginDate(new Date())
                .setNickName(userInfo.getNickName())
                .setAvatarUrl(userInfo.getAvatarUrl())
                .setCity(userInfo.getCity())
                .setCountry(user.getCountry())
                .setProvince(userInfo.getProvince())
                .setGender(userInfo.getGender());
        return userService.updateById(user);
    }
}
