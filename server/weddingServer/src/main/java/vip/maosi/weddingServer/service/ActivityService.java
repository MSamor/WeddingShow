package vip.maosi.weddingServer.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.val;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.maosi.weddingServer.domain.*;
import vip.maosi.weddingServer.dto.ActivityInfoDto;
import vip.maosi.weddingServer.mapper.ActivityMapper;
import vip.maosi.weddingServer.service.wx.WXService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ActivityService extends ServiceImpl<ActivityMapper, Activity> {
    @Autowired
    ActivityPrizeService activityPrizeService;
    @Autowired
    ActivityJoinService activityJoinService;
    @Autowired
    ActivityWinService activityWinService;
    @Autowired
    WXService wxService;

    public ActivityInfoDto getActivityInfo(String code) {
        final Activity activity = getActivity(code);
        if (activity == null) return null;
        val activityInfoDto = new ActivityInfoDto();
        BeanUtils.copyProperties(activity, activityInfoDto);
        val activityPrize = activityPrizeService.list(Wrappers
                .<ActivityPrize>lambdaQuery()
                .eq(ActivityPrize::getActivityId, activity.getId())
                .orderByAsc(ActivityPrize::getOrder));
        activityInfoDto.setPrizeList(activityPrize);
        return activityInfoDto;
    }

    public Pair<Integer, String> joinActivity(String openid, String code) {
        val activity = getActivity(code);
        val user = wxService.getUser(openid);
        if (user == null) return Pair.of(-1, "用户不存在");
        if (activity == null) return Pair.of(-1, "活动不存在");
        val join = activityJoinService.getOne(Wrappers.<ActivityJoin>lambdaQuery().eq(ActivityJoin::getUid, user.getId()));
        if (activity.getActivityEndDate().before(new Date())) return Pair.of(-1, "活动已截止");
        if (join != null) {
            return Pair.of(-1, "已加入活动");
        }
        val save = activityJoinService.save(new ActivityJoin()
                .setActivityId(activity.getId())
                .setUid(user.getId())
                .setDate(new Date()));
        if (save) return Pair.of(0, "加入成功");
        return Pair.of(-2, "加入失败");
    }

    public Triple<Integer, ActivityPrize, String> getActivityStatus(String openid, String code) {
        val activity = getActivity(code);
        val user = wxService.getUser(openid);
        if (user == null) return Triple.of(-1, null, "用户不存在");
        if (activity == null) return Triple.of(-1, null, "活动不存在");
        val exists = activityJoinService.getBaseMapper().exists(Wrappers.<ActivityJoin>lambdaQuery()
                .eq(ActivityJoin::getActivityId, activity.getId())
                .eq(ActivityJoin::getUid, user.getId()));
        if (exists && activity.getActivityEndDate().after(new Date())) {
            return Triple.of(1, null, "已加入活动，活动没开奖");
        } else if (exists && activity.getActivityEndDate().before(new Date())) {
            // 判断是否开奖
            val existsWin = activityWinService.getBaseMapper().exists(Wrappers.<ActivityWin>lambdaQuery()
                    .eq(ActivityWin::getActivityId, activity.getId()));
            if (existsWin) {
                // 判断是否中奖
                return isUserPrize(activity, user);
            } else {
                // 未开奖，开奖操作并返回中奖状态
                val activityPrizes = activityPrizeService.list(Wrappers.<ActivityPrize>lambdaQuery().eq(ActivityPrize::getActivityId, activity.getId()));
                val activityJoins = activityJoinService.list(Wrappers.<ActivityJoin>lambdaQuery().eq(ActivityJoin::getActivityId, activity.getId()));
                val winUserIdList = new ArrayList<Integer>();
                for (ActivityPrize activityPrize : activityPrizes) {
                    for (int i = 0; i < activityPrize.getCount(); i++) {
                        ActivityJoin join;
                        do {
                            join = getRandomValue(activityJoins);
                        } while (winUserIdList.contains(join.getUid()));
                        winUserIdList.add(join.getUid());
                        val activityWin = new ActivityWin();
                        activityWin.setActivityId(activity.getId())
                                .setUid(join.getUid())
                                .setActivityPrizeId(activityPrize.getId())
                                .setDate(new Date());
                        activityWinService.save(activityWin);
                    }
                }
                // 判断是否中奖
                return isUserPrize(activity, user);
            }
        }
        return Triple.of(0, null, "未加入当前活动");
    }

    public static <T> T getRandomValue(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    @NotNull
    private Triple<Integer, ActivityPrize, String> isUserPrize(Activity activity, User user) {
        // 已开奖，查询win表，返回中奖状态
        val winUser = activityWinService.getOne(Wrappers.<ActivityWin>lambdaQuery()
                .eq(ActivityWin::getUid, user.getId())
                .eq(ActivityWin::getActivityId, activity.getId()));
        if (winUser != null) {
            // 中奖
            val activityPrize = activityPrizeService.getById(winUser.getActivityPrizeId());
            return Triple.of(2, activityPrize, "恭喜你，已中奖！");
        } else {
            // 未中奖
            return Triple.of(3, null, "很遗憾，未中奖！");
        }
    }

    private Activity getActivity(String code) {
        return getOne(Wrappers.<Activity>lambdaQuery().eq(Activity::getCode, code));
    }
}
