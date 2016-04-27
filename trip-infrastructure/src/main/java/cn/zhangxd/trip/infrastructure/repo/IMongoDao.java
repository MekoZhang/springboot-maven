package cn.zhangxd.trip.infrastructure.repo;

import cn.zhangxd.trip.infrastructure.entity.TripUser;

import java.util.List;

/**
 * Created by zhangxd on 16/3/10.
 */
public interface IMongoDao {

    List<TripUser> findUsers();

}
