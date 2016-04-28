package cn.zhangxd.trip.infrastructure.repo;

import cn.zhangxd.trip.infrastructure.entity.TripUserPo;

import java.util.List;

/**
 * Created by zhangxd on 16/3/10.
 */
public interface IMongoDao {

    List<TripUserPo> findUsers();

}
