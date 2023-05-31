package vip.maosi.weddingserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.maosi.weddingserver.domain.Bullet;
import vip.maosi.weddingserver.meta.SessionKeys;
import vip.maosi.weddingserver.response.DefinedCode;
import vip.maosi.weddingserver.response.RGenerator;
import vip.maosi.weddingserver.response.ResEntity;
import vip.maosi.weddingserver.service.BulletService;
import vip.maosi.weddingserver.service.UserService;
import vip.maosi.weddingserver.service.wx.WXService;
import vip.maosi.weddingserver.util.SessionManagerUtils;

import java.util.Date;

/**
 * 弹幕相关接口
 */
@Validated
@RestController
@RequestMapping("/bullet")
public class BulletController {

    @Autowired
    BulletService bulletService;
    @Autowired
    UserService userService;
    @Autowired
    WXService wxService;
    @Autowired
    SessionManagerUtils sessionManagerUtils;

    /**
     * 分页获取自己发送的弹幕列表
     *
     * @param pageNum  页码
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("/getBulletsByOpenid")
    public ResEntity<Page<Bullet>> getBulletsByOpenid(HttpServletRequest request,
                                                      @RequestParam @Min(message = "不能小于1", value = 1) Integer pageNum,
                                                      @RequestParam @Min(message = "不能小于1", value = 1) Integer pageSize) {
        val openid = request.getHeader("openid");
        val user = wxService.getUser(openid);
        if (user == null) return RGenerator.resCustom(-1, "用户不存在");
        val page = bulletService.page(new Page<>(pageNum, pageSize),
                Wrappers.<Bullet>lambdaQuery()
                        .eq(Bullet::getUid, user.getId()));
        return RGenerator.resSuccess(page);
    }

    /**
     * 发送弹幕
     *
     * @param text 发送文本
     * @return
     */
    @GetMapping("/sendBullet")
    public ResEntity<String> sendBullet(HttpServletRequest request,
                                        @RequestParam @NotBlank(message = "发送内容不能为空") String text) {
        val openid = request.getHeader("openid");
        val user = wxService.getUser(openid);
        if (user == null) return RGenerator.resCustom(-1, "用户不存在");
        val bullet = new Bullet()
                .setDate(new Date())
                .setUid(user.getId())
                .setText(text);
        val save = bulletService.save(bullet);
        if (save) {
            sessionManagerUtils.sendMessageToAll(SessionKeys.WEDDING_SHOW.name(), "发送弹幕", text, DefinedCode.SUCCESS);
            return RGenerator.resSuccess("发送成功");
        }
        return RGenerator.resCustom(-1, "发送失败");
    }
}
