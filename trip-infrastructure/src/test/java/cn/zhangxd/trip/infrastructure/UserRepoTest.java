package cn.zhangxd.trip.infrastructure;

import cn.zhangxd.trip.Application;
import cn.zhangxd.trip.infrastructure.entity.User;
import cn.zhangxd.trip.infrastructure.mapper.UserMapper;
import cn.zhangxd.trip.infrastructure.repo.IMongoDao;
import cn.zhangxd.trip.infrastructure.repo.IRedisDao;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserRepoTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    IRedisDao redisDao;

    @Autowired
    IMongoDao mongoDao;

    @Test
    public void testMybatis() {
        List<User> users = userMapper.findUsersLike("");
        System.out.println("==========================:" + (users.size()));
    }

    @Test
    public void testMybatis1() {
        List<User> users = userMapper.findList(new User());
        System.out.println("==========================:" + (users.size()));
    }

    @Test
    public void testMybatis2() {
        List<User> users = userMapper.findList(new User(), new RowBounds(0, 5));
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
