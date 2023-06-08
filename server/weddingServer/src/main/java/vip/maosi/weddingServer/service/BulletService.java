package vip.maosi.weddingServer.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.val;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.maosi.weddingServer.domain.Bullet;
import vip.maosi.weddingServer.domain.User;
import vip.maosi.weddingServer.mapper.BulletMapper;

import java.util.Date;

@Service
public class BulletService extends ServiceImpl<BulletMapper, Bullet> {

    @Autowired
    UserService userService;

    public Pair<Integer, String> banUserByBulletId(Integer bulletId) {
        val bullet = getById(bulletId);
        val update = userService.update(Wrappers.<User>lambdaUpdate()
                .eq(User::getId, bullet.getUid())
                .set(User::getBan, true)
                .set(User::getBanDate, new Date()));
        if (update) return Pair.of(0, "禁用成功");
        else return Pair.of(-1, "禁用失败");
    }

}
