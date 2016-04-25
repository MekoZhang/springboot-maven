package cn.zhangxd.trip.infrastructure;

import cn.zhangxd.trip.Application;
import cn.zhangxd.trip.infrastructure.entity.User;
import cn.zhangxd.trip.infrastructure.repo.IMongoDao;
import cn.zhangxd.trip.infrastructure.repo.IRedisDao;
import cn.zhangxd.trip.infrastructure.repo.UserMapper;
import cn.zhangxd.trip.infrastructure.repo.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserRepoTest {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;

    @Autowired
    IRedisDao redisDao;

    @Autowired
    IMongoDao mongoDao;

    @Test
    public void test() {
        Random r = new Random();
        User user = new User();
        user.setPassword(UUID.randomUUID().toString());
        user.setUsername("admin" + r.nextInt());
        userRepo.save(user);
        System.out.println("==========================:" + (userRepo.findAll().size()));
    }

    @Test
    public void testMybatis() {
        List<User> users = userMapper.findUsersLike("");
        System.out.println("==========================:" + (users.size()));
    }

    @Test
    public void testRedisDao() {
        redisDao.findUsers();
    }

    @Test
    public void testMongoDao() {
        mongoDao.findUsers();
    }
}
