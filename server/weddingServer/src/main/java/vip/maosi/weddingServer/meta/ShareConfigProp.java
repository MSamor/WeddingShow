package vip.maosi.weddingServer.meta;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "share")
public class ShareConfigProp {

    public String link;
    public String title;
    public String desc;
    public String imageUrl;

}

