package cn.zhangxd.trip.infrastructure.mapper;


import cn.zhangxd.trip.infrastructure.config.CrudDao;
import cn.zhangxd.trip.infrastructure.config.annotation.MyBatisDao;
import cn.zhangxd.trip.infrastructure.entity.TripUser;

import java.util.List;

@MyBatisDao
public interface TripUserMapper extends CrudDao<TripUser> {

	List<TripUser> findUsersLike(String name);

}
