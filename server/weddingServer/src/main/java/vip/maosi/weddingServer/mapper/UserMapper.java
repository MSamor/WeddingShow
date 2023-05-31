package vip.maosi.weddingServer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.maosi.weddingServer.domain.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}