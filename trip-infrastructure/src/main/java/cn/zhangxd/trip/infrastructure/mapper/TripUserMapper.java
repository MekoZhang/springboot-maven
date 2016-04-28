package cn.zhangxd.trip.infrastructure.mapper;


import cn.zhangxd.trip.infrastructure.config.CrudDao;
import cn.zhangxd.trip.infrastructure.config.annotation.MyBatisDao;
import cn.zhangxd.trip.infrastructure.entity.TripUserPo;

@MyBatisDao
public interface TripUserMapper extends CrudDao<TripUserPo> {

	TripUserPo findByLogin(String login);

}
