package cn.zhangxd.trip.infrastructure.config;

import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * DAO支持类实现
 * @param <T>
 */
public interface CrudDao<T> extends BaseDao {

	/**
	 * 获取单条数据
	 * @param id 主键
	 * @return T
	 */
	T get(String id);
	
	/**
	 * 获取单条数据
	 * @param entity T
	 * @return T
	 */
	T get(T entity);
	
	/**
	 * 查询数据列表
	 * @param entity T
	 * @return List<T>
	 */
	List<T> findList(T entity);

    /**
     * 查询数据列表, 分页
     * @param entity T
     * @return List<T>
     */
    List<T> findList(T entity, RowBounds rowBounds);


	/**
	 * 插入数据
	 * @param entity T
	 * @return int
	 */
	int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity T
	 * @return int
	 */
	int update(T entity);
	
	/**
	 * 删除数据
	 * @param entity T
	 * @return int
	 */
	int delete(T entity);
	
}