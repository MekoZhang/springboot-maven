package cn.zhangxd.trip.infrastructure.repo;

import cn.zhangxd.trip.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
