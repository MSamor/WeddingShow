package vip.maosi.weddingServer.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import vip.maosi.weddingServer.domain.Bullet;

@Data
@Accessors(chain = true)
public class BulletManageDto extends Bullet {
    private Boolean isUserBan;
    private String nickName;
    private String avatarUrl;
}
