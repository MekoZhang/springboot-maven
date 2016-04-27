package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.entity.TripUser;
import cn.zhangxd.trip.infrastructure.mapper.TripUserMapper;
import cn.zhangxd.trip.service.api.service.UserService;
import cn.zhangxd.trip.service.api.vo.UserVo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxd on 16/4/18.
 */
@Service(protocol = {"dubbo"}, timeout = 5000)
public class UserServiceImpl implements UserService {

    @Autowired
    private TripUserMapper userMapper;

    @Override
    public List<UserVo> findUser() {
        List<TripUser> userList = userMapper.findUsersLike("");
        List<UserVo> userVoList = new ArrayList<>();
        for (TripUser user : userList) {
            UserVo userVo = new UserVo();
            userVo.setUsername(user.getName());
            userVo.setPassword(user.getPassword());
            userVoList.add(userVo);
        }

        return userVoList;
    }
}
