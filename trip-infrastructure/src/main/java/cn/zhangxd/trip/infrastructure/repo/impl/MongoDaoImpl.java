package cn.zhangxd.trip.infrastructure.repo.impl;

import cn.zhangxd.trip.infrastructure.entity.TripUserPo;
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
    public List<TripUserPo> findUsers() {
        TripUserPo a = new TripUserPo();
//        a.setId(123l);
        TripUserPo b = new TripUserPo();
//        b.setId(456l);
        mongoTemplate.save(a);
        mongoTemplate.save(b);

        logger.info("mongoDao");
        for (TripUserPo user : mongoTemplate.findAll(TripUserPo.class)) {
            System.out.println(user.getId());
        }
        return null;
    }
}
