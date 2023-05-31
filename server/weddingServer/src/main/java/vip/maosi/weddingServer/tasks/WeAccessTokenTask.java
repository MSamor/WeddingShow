package vip.maosi.weddingServer.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vip.maosi.weddingServer.service.wx.WeAccessTokens;

import java.util.concurrent.TimeUnit;

@Component
public class WeAccessTokenTask {
    @Autowired
    WeAccessTokens wxAccessTokens;

    @Scheduled(fixedRate = 7000, timeUnit = TimeUnit.SECONDS)
    public void refreshAccessToken() {
//        wxAccessTokens.refreshAccessToken();
    }
}
