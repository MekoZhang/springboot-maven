package cn.zhangxd.trip.infrastructure.mapper;

import cn.zhangxd.trip.infrastructure.config.CrudDao;
import cn.zhangxd.trip.infrastructure.config.annotation.MyBatisDao;
import cn.zhangxd.trip.service.api.entity.CmsCategory;

import java.util.List;

/**
 * 栏目DAO接口
 * Created by zhangxd on 15/10/20.
 */
@MyBatisDao
public interface CmsCategoryMapper extends CrudDao<CmsCategory> {

    /**
     * 找到所有子节点
     *
     * @param entity
     * @return
     */
    List<CmsCategory> findByParentIdsLike(CmsCategory entity);

    /**
     * 更新所有父节点字段
     *
     * @param entity
     * @return
     */
    int updateParentIds(CmsCategory entity);

    List<CmsCategory> findByParentId(CmsCategory entity);

}
