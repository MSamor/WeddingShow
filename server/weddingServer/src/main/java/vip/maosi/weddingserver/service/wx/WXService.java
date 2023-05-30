package vip.maosi.weddingserver.service.wx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import vip.maosi.weddingserver.http.HttpClient;
import vip.maosi.weddingserver.meta.TokenProp;

import java.util.Map;

@Service
@Slf4j
public class WXService {
    @Autowired
    TokenProp tokenProp;

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
}
