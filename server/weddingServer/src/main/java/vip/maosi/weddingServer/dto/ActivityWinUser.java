package vip.maosi.weddingServer.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ActivityWinUser {
    private String userName;
    private String avatarUrl;
    private String openid;
    private String prizeName;
    private Integer prizeNum;
}
