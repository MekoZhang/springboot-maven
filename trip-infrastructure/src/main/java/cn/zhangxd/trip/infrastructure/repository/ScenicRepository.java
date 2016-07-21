package cn.zhangxd.trip.infrastructure.repository;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mongo Demo
 * Created by zhangxd on 16/7/16.
 */
@Repository
public class ScenicRepository {

    @Autowired
    protected MongoTemplate mongoTemplate;

    public List<Scenic> getPageList() {
        Point point = new Point(127.177554, 37.241086);
        NearQuery nearQuery = NearQuery.near(point).maxDistance(new Distance(1000, Metrics.MILES));
        GeoResults<Scenic> results =  mongoTemplate.geoNear(nearQuery, Scenic.class);
        System.out.println(new Gson().toJson(results));
        Query query = new Query(Criteria.where("location").near(point).maxDistance(1));
        query.skip(0);
        query.limit(5000);
        return mongoTemplate.find(query, Scenic.class);
    }

}
