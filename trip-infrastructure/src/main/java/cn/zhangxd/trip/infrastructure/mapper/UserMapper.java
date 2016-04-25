package cn.zhangxd.trip.infrastructure.mapper;


import cn.zhangxd.trip.infrastructure.config.CrudDao;
import cn.zhangxd.trip.infrastructure.config.annotation.MyBatisDao;
import cn.zhangxd.trip.infrastructure.entity.User;

import java.util.List;

@MyBatisDao
public interface UserMapper extends CrudDao<User> {

	List<User> findUsersLike(String name);

}
