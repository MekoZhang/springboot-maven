package cn.zhangxd.trip.service.api.service;

import cn.zhangxd.trip.service.api.entity.SysMenu;
import cn.zhangxd.trip.service.api.entity.SysRole;
import cn.zhangxd.trip.service.api.entity.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * Created by zhangxd on 15/10/20.
 */
public interface ISystemService {


    //-- User Service --//

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    SysUser getUser(String id);

    /**
     * 根据登录名获取用户
     *
     * @param loginName
     * @return
     */
    SysUser getUserByLoginName(String loginName);

    PageInfo<SysUser> findUser(Page page, SysUser user);

    /**
     * 无分页查询人员列表
     *
     * @param user
     * @return
     */
    List<SysUser> findUser(SysUser user);

    void saveUser(SysUser user);

    void updateUserInfo(SysUser user);

    void deleteUser(SysUser user);

    void updatePasswordById(String id, String loginName, String newPassword);

    void updateUserLoginInfo(SysUser user);

    //-- Role Service --//

    SysRole getRole(String id);

    SysRole getRoleByName(String name);

    List<SysRole> findRole(SysRole role);

    List<SysRole> findAllRole(SysUser currentUser);

    void saveRole(SysRole role);

    void deleteRole(SysRole role);

    Boolean outUserInRole(SysRole role, SysUser user);

    SysUser assignUserToRole(SysRole role, SysUser user);

    //-- Menu Service --//

    SysMenu getMenu(String id);

    List<SysMenu> findAllMenu(SysUser currentUser);

    void saveMenu(SysMenu menu);

    void updateMenuSort(SysMenu menu);

    void deleteMenu(SysMenu menu);

}
