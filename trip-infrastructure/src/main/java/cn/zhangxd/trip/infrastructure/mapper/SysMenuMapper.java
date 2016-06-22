package cn.zhangxd.trip.infrastructure.mapper;

import cn.zhangxd.trip.infrastructure.config.CrudDao;
import cn.zhangxd.trip.infrastructure.config.annotation.MyBatisDao;
import cn.zhangxd.trip.service.api.entity.SysMenu;

import java.util.List;

/**
 * 菜单DAO接口
 * Created by zhangxd on 15/10/20.
 */
@MyBatisDao
public interface SysMenuMapper extends CrudDao<SysMenu> {

    List<SysMenu> findByParentIdsLike(SysMenu menu);

    List<SysMenu> findByUserId(SysMenu menu);

    int updateParentIds(SysMenu menu);

    int updateSort(SysMenu menu);

}
