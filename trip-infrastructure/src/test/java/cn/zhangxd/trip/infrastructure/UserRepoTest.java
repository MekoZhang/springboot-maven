package cn.zhangxd.trip.infrastructure;

import cn.zhangxd.trip.Application;
import cn.zhangxd.trip.infrastructure.entity.TripUser;
import cn.zhangxd.trip.infrastructure.mapper.TripUserMapper;
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
    TripUserMapper userMapper;

    @Test
    public void testMybatis() {
        List<TripUser> users = userMapper.findUsersLike("");
        System.out.println("==========================:" + (users.size()));
    }

    @Test
    public void testMybatis1() {
        List<TripUser> users = userMapper.findList(new TripUser());
        System.out.println("==========================:" + (users.size()));
    }

    @Test
    public void testMybatis2() {
        List<TripUser> users = userMapper.findList(new TripUser(), new RowBounds(0, 5));
        System.out.println("==========================:" + (users.size()));
    }

}
