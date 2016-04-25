package cn.zhangxd.trip.infrastructure.repo.impl;

import cn.zhangxd.trip.infrastructure.entity.User;
import cn.zhangxd.trip.infrastructure.repo.IMongoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangxd on 16/4/18.
 */
@Repository
public class MongoDaoImpl implements IMongoDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> findUsers() {
        User a = new User();
        a.setId(123l);
        User b = new User();
        b.setId(456l);
        mongoTemplate.save(a);
        mongoTemplate.save(b);

        logger.info("mongoDao");
        for (User user : mongoTemplate.findAll(User.class)) {
            System.out.println(user.getId());
        }
        return null;
    }
}
