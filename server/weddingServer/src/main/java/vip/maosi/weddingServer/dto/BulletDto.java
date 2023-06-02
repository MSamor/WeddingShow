package vip.maosi.weddingServer.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BulletDto {
    private String nickName;
    private String url;
    private String text;
}
