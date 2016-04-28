package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.entity.TripUserPo;
import cn.zhangxd.trip.infrastructure.mapper.TripUserMapper;
import cn.zhangxd.trip.service.api.vo.TripUser;
import cn.zhangxd.trip.util.BeanHelper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义UserDetailsService
 * Created by zhangxd on 16/3/17.
 */
@Service(protocol = {"dubbo"}, timeout = 5000)
public class CustomUserDetailsService implements UserDetailsService {

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

}
