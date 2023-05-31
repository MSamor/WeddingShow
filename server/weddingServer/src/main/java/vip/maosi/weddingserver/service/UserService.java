package vip.maosi.weddingserver.service;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.maosi.weddingserver.domain.User;
import vip.maosi.weddingserver.mapper.UserMapper;
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
