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
public class WeJsApiTicket {
    @Autowired
    WeAccessTokens weAccessTokens;

    /**
     * accessToken 有效期，默认 7000s
     */
    private long expiresIn = 7000L;

    private String ticket = "";

    private CountDownLatch latch = null;

    public long getExpiresIn() {
        return expiresIn;
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String getTicket() {
        if (ticket.isEmpty()) {
            final var latch = new CountDownLatch(1);
            this.latch = latch;
            try {
                latch.await(60L, TimeUnit.SECONDS);
            } catch (Exception ignored) {
            }
        }
        return ticket;
    }

    public synchronized String refreshTicket() {
        final var url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
        final var params = Map.of(
                "access_token", weAccessTokens.getAccessToken(),
                "type", "jsapi"
        );
        var ticket = "";
        try {
            final String response = HttpClient.get().get(url, params);
            if (response != null) {
                final var jo = new JSONObject(response);
                if (!jo.has("errcode") || jo.getInt("errcode") == 0) {
                    ticket = jo.optString("ticket");
                    expiresIn = jo.getLong("expires_in");
                }
            }
        } catch (Exception e) {
            log.error("微信获取ticket失败", e);
        }
        this.ticket = ticket;
        final var latch = this.latch;
        if (latch != null) {
            latch.countDown();
            this.latch = null;
        }
        log.info("微信获取ticket: " + ticket);
        return ticket;
    }
}
