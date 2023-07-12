package vip.maosi.weddingServer.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ParamWeMessageCheck {
    private Integer version;
    private String openid;
    private Integer scene;
    private String content;
    //private String nickname;
    //private String title;
    //private String signature;
}
