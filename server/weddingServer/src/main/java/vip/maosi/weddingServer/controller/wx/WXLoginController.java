package vip.maosi.weddingServer.controller.wx;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.maosi.weddingServer.dto.WXUserInfoDto;
import vip.maosi.weddingServer.response.RGenerator;
import vip.maosi.weddingServer.response.ResEntity;
import vip.maosi.weddingServer.service.wx.WXService;

/**
 * 微信登录相关接口
 */
@RestController
@Validated
@RequestMapping("/wx")
public class WXLoginController {

    @Autowired
    WXService wxService;

    /**
     * 静默登录
     * @param code
     * @return
     */
    @GetMapping("/silentLogin")
    public ResEntity<String> silentLogin(@RequestParam String code) {
        val login = wxService.silentLogin(code);
        if (login) {
            return RGenerator.resSuccess("静默登录成功");
        }
        return RGenerator.resCustom(-1, "静默登录失败，请重试");
    }

    /**
     * 获取头像昵称
     *
     * @param userInfo 用户信息
     * @return
     */
    @PostMapping("/login")
    public ResEntity<String> login(HttpServletRequest request, @RequestBody @Validated WXUserInfoDto userInfo) {
        val openid = request.getHeader("openid");
        val login = wxService.login(openid, userInfo);
        if (login) {
            return RGenerator.resSuccess("登录成功");
        }
        return RGenerator.resCustom(-1,"登录失败,请重试");
    }
}
