package cn.zhangxd.trip.service.api.service;

import cn.zhangxd.trip.service.api.entity.TripUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户接口
 * Created by zhangxd on 16/4/18.
 */
public interface ITripUserService extends UserDetailsService {

    TripUser findUserByMobile(String mobile);

    void save(TripUser entity);

    void updatePasswordByMobile(TripUser entity);
}
