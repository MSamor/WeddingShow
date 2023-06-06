package vip.maosi.weddingServer.controller.wx;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.maosi.weddingServer.domain.User;
import vip.maosi.weddingServer.dto.WXUserInfoDto;
import vip.maosi.weddingServer.response.RGenerator;
import vip.maosi.weddingServer.response.ResEntity;
import vip.maosi.weddingServer.service.UserService;
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
    @Autowired
    UserService userService;

    /**
     * 静默登录
     *
     * @param code
     * @return
     */
    @GetMapping("/silentLogin")
    public ResEntity<String> silentLogin(@RequestParam String code) {
        val openid = wxService.silentLogin(code);
        if (StringUtils.isNotEmpty(openid)) {
            return RGenerator.resSuccess("静默登录成功", openid);
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
        return RGenerator.resCustom(-1, "登录失败,请重试");
    }


    /**
     * 加载头像昵称
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public ResEntity<User> getUserInfo(HttpServletRequest request) {
        val openid = request.getHeader("openid");
        val user = wxService.getUser(openid);
        if (user != null) {
            return RGenerator.resSuccess(user);
        }
        return RGenerator.resCustom(-1, "获取失败,请重试");
    }
}
