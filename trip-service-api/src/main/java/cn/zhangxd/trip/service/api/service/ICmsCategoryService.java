package cn.zhangxd.trip.service.api.service;

import cn.zhangxd.trip.service.api.entity.CmsCategory;

import java.util.List;

/**
 * 栏目Service
 * Created by zhangxd on 15/10/20.
 */
public interface ICmsCategoryService {

    CmsCategory get(String id);

    List<CmsCategory> findByUser(String module);

    void save(CmsCategory category);

    void delete(CmsCategory category);

    List<CmsCategory> findByParentId(String parentId);

}
