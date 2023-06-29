package vip.maosi.weddingServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.maosi.weddingServer.domain.ActivityPrize;
import vip.maosi.weddingServer.dto.ActivityInfoDto;
import vip.maosi.weddingServer.dto.ActivityWinUser;
import vip.maosi.weddingServer.response.RGenerator;
import vip.maosi.weddingServer.response.ResEntity;
import vip.maosi.weddingServer.service.ActivityJoinService;
import vip.maosi.weddingServer.service.ActivityPrizeService;
import vip.maosi.weddingServer.service.ActivityService;
import vip.maosi.weddingServer.service.ActivityWinService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityPrizeService activityPrizeService;
    @Autowired
    ActivityJoinService activityJoinService;
    @Autowired
    ActivityWinService activityWinService;
    @Autowired
    BulletController bulletController;

    /**
     * 根据活动code获取活动详情和奖品列表
     *
     * @param code
     * @return
     */
    @GetMapping("/getActivityPrizes")
    public ResEntity<ActivityInfoDto> getActivityPrizes(@RequestParam @NotBlank(message = "code不能为空") String code) {
        val activityInfo = activityService.getActivityInfo(code);
        if (activityInfo == null) return RGenerator.resCustom(-1, "没有活动信息");
        return RGenerator.resSuccess(activityInfo);
    }

    /**
     * 加入活动
     *
     * @param code
     * @return
     */
    @GetMapping("/joinActivity")
    public ResEntity<String> joinActivity(HttpServletRequest request,
                                          @RequestParam @NotBlank(message = "code不能为空") String code) {
        val openid = request.getHeader("openid");
        val pair = activityService.joinActivity(openid, code);
        if (pair.getLeft() == 0) {
            bulletController.sendBullet(request,"新婚快乐~");
            return RGenerator.resSuccess(pair.getRight());
        }
        return RGenerator.resCustom(pair.getLeft(), pair.getRight());
    }

    /**
     * 获取活动状态
     *
     * @param request
     * @param code    活动code
     * @return
     */
    @GetMapping("/getActivityStatus")
    public ResEntity<ActivityPrize> getActivityStatus(HttpServletRequest request,
                                                      @RequestParam @NotBlank(message = "code不能为空") String code) {
        val openid = request.getHeader("openid");
        val triple = activityService.getActivityStatus(openid, code);
        return RGenerator.resCustom(triple.getLeft(), triple.getRight(), triple.getMiddle());
    }

    /**
     * 查询中奖人列表
     * @param code
     * @return
     */
    @GetMapping("/getActivityWinList")
    public ResEntity<List<ActivityWinUser>> getActivityWinList(@RequestParam @NotBlank(message = "code不能为空") String code) {
        val list = activityService.getActivityWinList(code);
        return RGenerator.resSuccess(list);
    }
}
