package vip.maosi.weddingServer.meta;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AdminPasswordConfigProp {

    @Value("${admin-password}")
    private String adminPassword;

    @Value("${send-bullet-able}")
    private boolean sendBulletAble;
}
