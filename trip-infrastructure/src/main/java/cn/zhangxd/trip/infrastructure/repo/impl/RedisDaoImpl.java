package cn.zhangxd.trip.infrastructure.repo.impl;

import cn.zhangxd.trip.infrastructure.entity.TripUser;
import cn.zhangxd.trip.infrastructure.repo.IRedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangxd on 16/4/18.
 */
@Repository
public class RedisDaoImpl implements IRedisDao {

    private Logger logger = LoggerFactory.getLogger(RedisDaoImpl.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<TripUser> findUsers() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        String key = "spring.boot.redis.test";
        if (!redisTemplate.hasKey(key)) {
            ops.set(key, "foo");
        }

        logger.info("Found key " + key + ", value=" + ops.get(key));

        return null;
    }
}
