package vip.maosi.weddingServer.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.maosi.weddingServer.response.RGenerator;
import vip.maosi.weddingServer.response.ResEntity;

@Validated
@RestController
@RequestMapping("/manage")
public class ManageController {

    @GetMapping("/login")
    public ResEntity<Boolean> login(@RequestParam @NotBlank(message = "密码不能为空") String pwd) {
        if ("wpxhkl".equals(pwd)) {
            return RGenerator.resSuccess("登录成功",true);
        }else {
            return RGenerator.resCustom(-3,"密码错误");
        }
    }
}
