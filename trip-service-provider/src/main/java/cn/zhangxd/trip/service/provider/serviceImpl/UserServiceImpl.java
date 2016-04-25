package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.entity.User;
import cn.zhangxd.trip.infrastructure.repo.UserMapper;
import cn.zhangxd.trip.service.api.service.IUserService;
import cn.zhangxd.trip.service.api.vo.UserVo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxd on 16/4/18.
 */
@Service(protocol = {"dubbo"}, timeout = 5000)
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> findUser() {
        List<User> userList = userMapper.findUsersLike("");
        List<UserVo> userVoList = new ArrayList<>();
        for (User user : userList) {
            UserVo userVo = new UserVo();
            userVo.setUsername(user.getUsername());
            userVo.setPassword(user.getPassword());
            userVoList.add(userVo);
        }

        return userVoList;
    }
}
