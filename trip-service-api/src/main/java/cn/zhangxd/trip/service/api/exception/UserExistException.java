package cn.zhangxd.trip.service.api.exception;

import cn.zhangxd.trip.service.api.exception.base.BusinessException;

/**
 * 用户已存在
 * Created by zhangxd on 16/5/8.
 */
public class UserExistException extends BusinessException {

    public UserExistException(String message) {
        super(message);
    }

}
