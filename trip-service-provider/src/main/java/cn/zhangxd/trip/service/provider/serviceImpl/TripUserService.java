package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.mapper.TripUserMapper;
import cn.zhangxd.trip.service.api.entity.TripUser;
import cn.zhangxd.trip.service.api.service.ITripUserService;
import cn.zhangxd.trip.service.provider.common.service.CrudService;
import com.alibaba.dubbo.config.annotation.Service;
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
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        TripUser user = dao.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", login));
        }
        return user;
    }

    @Override
    public TripUser findUserByMobile(String mobile) {
        return dao.findByMobile(mobile);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(TripUser entity) {
        if (entity.getIsNewRecord()) {
            entity.preInsert();
            dao.insert(entity);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePasswordByMobile(TripUser entity) {
        dao.updatePasswordByMobile(entity);
    }

}
