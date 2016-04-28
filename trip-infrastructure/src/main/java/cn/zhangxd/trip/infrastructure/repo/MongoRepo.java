package cn.zhangxd.trip.infrastructure.repo;

import com.mongodb.CommandResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MongoRepo<T> {

    private Logger logger = LoggerFactory.getLogger(MongoRepo.class);


    @Autowired
    protected MongoTemplate mongoTemplate;

    /**
     * 保存一个实体
     *
     * @param obj 实体对象
     */
    public void save(T obj) {
        logger.info("[Mongo Dao ]save:" + obj);
        this.mongoTemplate.save(obj);
    }

    /**
     * 删除一个实体
     *
     * @param obj 实体对象
     */
    public void delete(T obj) {
        logger.info("[Mongo Dao ]delete:" + obj);
        this.mongoTemplate.remove(obj);
    }


    /**
     * 查询一个实体
     *
     * @param query 查询条件
     * @param clazz 对象类型
     * @return 实体对象
     */
    public T queryOne(Query query, Class<T> clazz) {
        logger.info("[Mongo Dao ]queryOne:" + query);
        return this.mongoTemplate.findOne(query, clazz);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @param start 开始
     * @param size 条数
     * @param clazz 对象类型
     * @return 实体对象列表
     */
    public List<T> getPageList(Query query, int start, int size, Class<T> clazz) {
        query.skip(start);
        query.limit(size);
        logger.info("[Mongo Dao ]queryPage:" + query + "(" + (start) + "," + (size) + ")");
        return this.mongoTemplate.find(query, clazz);
    }


    /**
     * 分页查询
     *
     * @param query 查询条件
     * @param clazz 对象类型
     * @return 实体对象列表
     */
    public List<T> getList(Query query, Class<T> clazz) {
        logger.info("[Mongo Dao ]queryPage:" + query);
        return this.mongoTemplate.find(query, clazz);
    }

    /**
     * 记录条数
     *
     * @param query 查询条件
     * @param clazz 对象类型
     * @return 记录条数
     */
    public Long getPageCount(Query query, Class<T> clazz) {
        logger.info("[Mongo Dao ]queryPageCount:" + query);
        return this.mongoTemplate.count(query, clazz);
    }

    /**
     * 修改第一个匹配的
     *
     * @param query 查询条件
     * @param update 更新内容
     * @param clazz 对象类型
     */
    public void updateFirst(Query query, Update update, Class<T> clazz) {
        logger.info("[Mongo Dao ]updateFirst:query(" + query + "'),update(" + update + ")");
        mongoTemplate.updateFirst(query, update, clazz);
    }

    /**
     * 按id删除
     *
     * @param id id
     * @param clazz 对象类型
     */
    public void deleteById(String id, Class<T> clazz) {
        Criteria criteria = Criteria.where("_id").in(id);
        if (null != criteria) {
            Query query = new Query(criteria);
            logger.info("===============[Mongo Dao ]deleteById:" + query);
            if (this.queryOne(query, clazz) != null) {
                mongoTemplate.remove(query);
            }
        }
    }


    /**
     * 执行命令
     *
     * @param command 命令
     */
    public CommandResult executeCommand(String command) {
        logger.info("===============[Mongo Dao ]executeCommand:" + command);
        return mongoTemplate.executeCommand(command);
    }

}
