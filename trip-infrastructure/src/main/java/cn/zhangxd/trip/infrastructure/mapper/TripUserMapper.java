package cn.zhangxd.trip.infrastructure.mapper;


import cn.zhangxd.trip.infrastructure.config.CrudDao;
import cn.zhangxd.trip.infrastructure.config.annotation.MyBatisDao;
import cn.zhangxd.trip.service.api.entity.TripUser;

@MyBatisDao
public interface TripUserMapper extends CrudDao<TripUser> {

    TripUser findByLogin(String login);

    TripUser findByMobile(String mobile);

    int updatePasswordByMobile(TripUser tripUser);
}
