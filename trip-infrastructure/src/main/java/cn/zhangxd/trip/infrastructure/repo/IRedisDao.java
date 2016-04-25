package cn.zhangxd.trip.infrastructure.repo;

import cn.zhangxd.trip.infrastructure.entity.User;

import java.util.List;

/**
 * Created by zhangxd on 16/4/18.
 */
public interface IRedisDao {
    List<User> findUsers();
}
