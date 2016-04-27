package cn.zhangxd.trip.infrastructure.repo.impl;

import cn.zhangxd.trip.infrastructure.entity.TripUser;
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
    public List<TripUser> findUsers() {
        TripUser a = new TripUser();
//        a.setId(123l);
        TripUser b = new TripUser();
//        b.setId(456l);
        mongoTemplate.save(a);
        mongoTemplate.save(b);

        logger.info("mongoDao");
        for (TripUser user : mongoTemplate.findAll(TripUser.class)) {
            System.out.println(user.getId());
        }
        return null;
    }
}
