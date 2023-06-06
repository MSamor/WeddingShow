package vip.maosi.weddingServer.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vip.maosi.weddingServer.service.wx.WeJsApiTicket;

import java.util.concurrent.TimeUnit;

@Component
public class WeTicketTask {
    @Autowired
    WeJsApiTicket weJsApiTicket;

    @Scheduled(fixedRate = 7000, timeUnit = TimeUnit.SECONDS)
    public void refreshAccessToken() {
        weJsApiTicket.refreshTicket();
    }
}
