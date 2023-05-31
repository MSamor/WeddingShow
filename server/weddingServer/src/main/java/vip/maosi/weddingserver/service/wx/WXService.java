package vip.maosi.weddingserver.service.wx;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import vip.maosi.weddingserver.domain.User;
import vip.maosi.weddingserver.dto.WXUserInfoDto;
import vip.maosi.weddingserver.http.HttpClient;
import vip.maosi.weddingserver.meta.TokenProp;
import vip.maosi.weddingserver.service.UserService;

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

    public boolean silentLogin(String code) {
        if (StringUtils.isEmpty(code)) return false;
        val openid = getOpenid(code);
        final User user = getUser(openid);
        if (user == null) {
            val newUser = new User()
                    .setLastLoginDate(new Date())
                    .setSignDate(new Date())
                    .setOpenid(openid);
            return userService.save(newUser);
        }
        user.setLastLoginDate(new Date());
        return userService.updateById(user);
    }

    public User getUser(String openid) {
        return userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getOpenid, openid));
    }

    public boolean login(String openid, WXUserInfoDto userInfo) {
        val user = getUser(openid);
        if (user == null) return false;
        user.setLastLoginDate(new Date())
                .setNickName(userInfo.getNickName())
                .setAvatarUrl(userInfo.getAvatarUrl());
        return userService.updateById(user);
    }
}
