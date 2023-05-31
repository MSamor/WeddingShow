package vip.maosi.weddingServer.service;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.maosi.weddingServer.domain.User;
import vip.maosi.weddingServer.mapper.UserMapper;
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
