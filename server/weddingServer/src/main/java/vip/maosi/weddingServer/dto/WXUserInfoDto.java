package vip.maosi.weddingServer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WXUserInfoDto {
    @NotBlank(message = "头像不能为空")
    private String avatarUrl;
    @NotBlank(message = "昵称不能为空")
    private String nickName;
}
