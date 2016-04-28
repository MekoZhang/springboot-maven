package cn.zhangxd.trip.infrastructure.repo;

import cn.zhangxd.trip.infrastructure.entity.TripUserPo;

import java.util.List;

/**
 * Created by zhangxd on 16/4/18.
 */
public interface IRedisDao {
    List<TripUserPo> findUsers();
}
