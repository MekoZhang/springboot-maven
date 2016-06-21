package cn.zhangxd.trip.infrastructure.mapper;

import cn.zhangxd.trip.infrastructure.config.CrudDao;
import cn.zhangxd.trip.infrastructure.config.annotation.MyBatisDao;
import cn.zhangxd.trip.service.api.entity.SysRole;

/**
 * 角色DAO接口
 * Created by zhangxd on 15/10/20.
 */
@MyBatisDao
public interface SysRoleMapper extends CrudDao<SysRole> {

    SysRole getByName(SysRole role);
	
	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	int deleteRoleMenu(SysRole role);

	int insertRoleMenu(SysRole role);
	
}
