package vip.maosi.weddingServer.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.maosi.weddingServer.domain.User;
import vip.maosi.weddingServer.mapper.ActivityWinMapper;
import vip.maosi.weddingServer.domain.ActivityWin;
import vip.maosi.weddingServer.service.wx.WXService;

@Service
public class ActivityWinService extends ServiceImpl<ActivityWinMapper, ActivityWin> {

    @Autowired
    WXService wxService;

    public boolean isGetByOpenid(String openid, Boolean state) {
        User user = wxService.getUser(openid);
        if (user == null) return false;
        var updateWrapper = new LambdaUpdateWrapper<ActivityWin>();
        updateWrapper.eq(ActivityWin::getUid, user.getId())
                .set(ActivityWin::getIsGet, state);
        return update(updateWrapper);
    }

}
