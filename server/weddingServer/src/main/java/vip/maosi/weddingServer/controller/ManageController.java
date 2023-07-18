package vip.maosi.weddingServer.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.maosi.weddingServer.meta.AdminPasswordConfigProp;
import vip.maosi.weddingServer.response.RGenerator;
import vip.maosi.weddingServer.response.ResEntity;
import vip.maosi.weddingServer.service.ActivityWinService;

@Validated
@RestController
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    AdminPasswordConfigProp adminPasswordConfigProp;

    @Autowired
    ActivityWinService activityWinService;

    @GetMapping("/login")
    public ResEntity<Boolean> login(@RequestParam @NotBlank(message = "密码不能为空") String pwd) {
        if (adminPasswordConfigProp.getAdminPassword().equals(pwd)) {
            return RGenerator.resSuccess("登录成功", true);
        } else {
            return RGenerator.resCustom(-3, "密码错误");
        }
    }

    @GetMapping("/setting")
    public ResEntity<Boolean> login() {
        return RGenerator.resSuccess(adminPasswordConfigProp.isSendBulletAble());
    }

    @GetMapping("/isget")
    public ResEntity<Boolean> isGetByOpenid(@RequestParam String openid, @RequestParam Boolean state) {
        boolean getByOpenid = activityWinService.isGetByOpenid(openid, state);
        if (getByOpenid) return RGenerator.resSuccess(getByOpenid);
        else
            return RGenerator.resCustom(-1, "设置失败");
    }
}
