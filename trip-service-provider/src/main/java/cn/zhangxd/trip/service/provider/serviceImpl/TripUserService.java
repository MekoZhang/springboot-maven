package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.mapper.TripUserMapper;
import cn.zhangxd.trip.service.api.entity.TripUser;
import cn.zhangxd.trip.service.api.exception.UserNotFoundException;
import cn.zhangxd.trip.service.api.service.ITripUserService;
import cn.zhangxd.trip.service.provider.common.service.CrudService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务实现
 * Created by zhangxd on 16/4/18.
 */
@Service(protocol = {"dubbo"}, timeout = 5000)
@Transactional(readOnly = true)
public class TripUserService extends CrudService<TripUserMapper, TripUser> implements ITripUserService {

    @Override
    @Cacheable(value = "tripUser", key = "'tripUser' + #username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TripUser user = dao.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return user;
    }

    @Override
    public TripUser findUserByLogin(String login) throws UserNotFoundException {
        TripUser tripUser = dao.findByLogin(login);

        if (tripUser == null) {
            throw new UserNotFoundException(String.format("User %s does not exist!", login));
        }

        return tripUser;
    }
}
