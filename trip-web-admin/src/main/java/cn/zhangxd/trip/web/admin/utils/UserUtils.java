package cn.zhangxd.trip.web.admin.utils;

import cn.zhangxd.trip.service.api.entity.SysMenu;
import cn.zhangxd.trip.service.api.entity.SysUser;
import cn.zhangxd.trip.service.api.service.ISystemService;
import cn.zhangxd.trip.web.admin.base.SpringContextHolder;
import cn.zhangxd.trip.web.admin.security.SystemAuthorizingRealm.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * 用户工具类
 * Created by zhangxd on 15/10/20.
 */
public class UserUtils {

    private static ISystemService systemService = SpringContextHolder.getBean(ISystemService.class);

    /**
     * 根据ID获取用户
     *
     * @param id
     * @return 取不到返回null
     */
    public static SysUser get(String id) {
        return systemService.getUser(id);
    }

    /**
     * 获取当前用户
     *
     * @return 取不到返回 new User()
     */
    public static SysUser getUser() {
        Principal principal = getPrincipal();
        if (principal != null) {
            SysUser user = get(principal.getId());
            if (user != null) {
                return user;
            }
            return new SysUser();
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new SysUser();
    }

    /**
     * 获取当前用户授权菜单
     *
     * @return
     */
    public static List<SysMenu> getMenuList() {
        return systemService.findAllMenu(getUser());
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static Principal getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal;
            }
        } catch (UnavailableSecurityManagerException | InvalidSessionException ignored) {
        }
        return null;
    }

    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException ignored) {

        }
        return null;
    }

}
