package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.entity.TripUserPo;
import cn.zhangxd.trip.infrastructure.mapper.TripUserMapper;
import cn.zhangxd.trip.service.api.exception.UserNotFoundException;
import cn.zhangxd.trip.service.api.service.TripUserService;
import cn.zhangxd.trip.service.api.vo.TripUser;
import cn.zhangxd.trip.util.BeanHelper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户服务实现
 * Created by zhangxd on 16/4/18.
 */
@Service(protocol = {"dubbo"}, timeout = 5000)
public class TripUserServiceImpl implements TripUserService {

    @Autowired
    private TripUserMapper tripUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TripUserPo user = tripUserMapper.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return (TripUser) BeanHelper.changeProValue(TripUserPo.class, TripUser.class, user);
    }

    @Override
    public TripUser findUserByLogin(String login) throws UserNotFoundException {
        TripUserPo tripUser = tripUserMapper.findByLogin(login);

        if (tripUser == null) {
            throw new UserNotFoundException(String.format("User %s does not exist!", login));
        }

        return (TripUser) BeanHelper.changeProValue(TripUserPo.class, TripUser.class, tripUser);
    }
}
