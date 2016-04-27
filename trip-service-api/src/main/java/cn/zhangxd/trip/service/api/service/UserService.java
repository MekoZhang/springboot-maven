package cn.zhangxd.trip.service.api.service;

import cn.zhangxd.trip.service.api.vo.UserVo;

import java.util.List;

/**
 * Created by zhangxd on 16/4/18.
 */
public interface UserService {

    List<UserVo> findUser();
}
