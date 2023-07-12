package vip.maosi.weddingServer.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.val;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.maosi.weddingServer.domain.Bullet;
import vip.maosi.weddingServer.dto.BulletDto;
import vip.maosi.weddingServer.dto.BulletManageDto;
import vip.maosi.weddingServer.dto.WeContentScene;
import vip.maosi.weddingServer.meta.SessionKeys;
import vip.maosi.weddingServer.response.DefinedCode;
import vip.maosi.weddingServer.response.RGenerator;
import vip.maosi.weddingServer.response.ResEntity;
import vip.maosi.weddingServer.service.BulletService;
import vip.maosi.weddingServer.service.UserService;
import vip.maosi.weddingServer.service.wx.WXService;
import vip.maosi.weddingServer.service.wx.WeMsgCheck;
import vip.maosi.weddingServer.util.JsonUtils;
import vip.maosi.weddingServer.util.SessionManagerUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    WeMsgCheck weMsgCheck;

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
                        .eq(Bullet::getUid, user.getId())
                        .orderByDesc(Bullet::getDate));
        return RGenerator.resSuccess(page);
    }

    /**
     * 获取所有用户发布的弹幕,flag为1 不返回禁用的用户
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getBulletList")
    public ResEntity<List<BulletManageDto>> getBulletList(@RequestParam @Min(message = "不能小于1", value = 1) Integer pageNum,
                                                          @RequestParam @Min(message = "不能小于1", value = 1) Integer pageSize,
                                                          @RequestParam(required = false) Integer flag) {
        val page = bulletService.page(new Page<>(pageNum, pageSize),
                Wrappers.<Bullet>lambdaQuery()
                        .orderByDesc(Bullet::getDate));
        val list = new ArrayList<BulletManageDto>();
        for (Bullet bullet : page.getRecords()) {
            val bulletManageDto = new BulletManageDto();
            BeanUtils.copyProperties(bullet, bulletManageDto);
            val bulletItem = bulletService.getById(bullet.getId());
            val user = userService.getById(bulletItem.getUid());
            if (user == null) continue;
            if (user.getBan() == null) bulletManageDto.setIsUserBan(false);
            else {
                bulletManageDto.setIsUserBan(user.getBan());
                if (flag == 1)
                    continue;
            }
            bulletManageDto.setNickName(user.getNickName());
            bulletManageDto.setAvatarUrl(user.getAvatarUrl());
            list.add(bulletManageDto);
        }
        return RGenerator.resSuccess(list);
    }


    /**
     * 禁用用户
     *
     * @return
     */
    @GetMapping("/banUserByBulletId")
    public ResEntity<String> banUserByBulletId(@RequestParam @Min(message = "不能小于1", value = 1) Integer bulletId,
                                               @RequestParam Boolean isBan) {
        Pair<Integer, String> pair;
        if (isBan) {
            pair = bulletService.banUserByBulletId(bulletId);
        } else {
            pair = bulletService.runUserByBulletId(bulletId);
        }
        if (pair.getLeft() == 0) return RGenerator.resSuccess(pair.getRight());
        return RGenerator.resCustom(pair.getLeft(), pair.getRight());
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
        if (user.getBan() != null && user.getBan()) return RGenerator.resCustom(-3, "用户已禁用");
        // 检测是否违规
        //  Pair<Integer, String> pair = weMsgCheck.checkMessage(openid, WeContentScene.COMMENT, text);
        //  if (pair.getLeft() != 0) return RGenerator.resCustom(-4, pair.getRight());
        val bullet = new Bullet()
                .setDate(new Date())
                .setUid(user.getId())
                .setText(text);
        val save = bulletService.save(bullet);
        if (save) {
            val bulletDto = new BulletDto();
            bulletDto.setUrl(user.getAvatarUrl())
                    .setNickName(user.getNickName())
                    .setText(text);
            sessionManagerUtils.sendMessageToAll(SessionKeys.WEDDING_SHOW.name(), "发送弹幕",
                    JsonUtils.toJson(bulletDto),
                    DefinedCode.BULLET);
            return RGenerator.resSuccess("发送成功");
        }
        return RGenerator.resCustom(-2, "发送失败");
    }
}
