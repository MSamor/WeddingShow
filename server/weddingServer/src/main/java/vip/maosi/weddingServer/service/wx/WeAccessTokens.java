package vip.maosi.weddingServer.service.wx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import vip.maosi.weddingServer.http.HttpClient;
import vip.maosi.weddingServer.meta.TokenProp;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@EnableConfigurationProperties(TokenProp.class)
public class WeAccessTokens {
    @Autowired
    TokenProp tokenProp;

    /**
     * accessToken 有效期，默认 7000s
     */
    private long expiresIn = 7000L;

    private String accessToken = "";

    private CountDownLatch latch = null;

    public long getExpiresIn() {
        return expiresIn;
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String getAccessToken() {
        if (accessToken.isEmpty()) {
            final var latch = new CountDownLatch(1);
            this.latch = latch;
            try {
                latch.await(60L, TimeUnit.SECONDS);
            } catch (Exception ignored) {
            }
        }
        return accessToken;
    }

    public synchronized String refreshAccessToken() {
        final var url = "https://api.weixin.qq.com/cgi-bin/token";
        final var params = Map.of(
                "grant_type", "client_credential",
                "appid", tokenProp.wechat.publicProgram.appId,
                "secret", tokenProp.wechat.publicProgram.appSecret
        );
        var accessToken = "";
        try {
            final String response = HttpClient.get().get(url, params);
            if (response != null) {
                final var jo = new JSONObject(response);
                if (!jo.has("errcode") || jo.getInt("errcode") == 0) {
                    accessToken = jo.optString("access_token");
                    expiresIn = jo.getLong("expires_in");
                }
            }
        } catch (Exception e) {
            log.error("微信获取accessToken失败", e);
        }
        this.accessToken = accessToken;
        final var latch = this.latch;
        if (latch != null) {
            latch.countDown();
            this.latch = null;
        }
        log.info("微信获取accessToken: " + accessToken);
        return accessToken;
    }
}
