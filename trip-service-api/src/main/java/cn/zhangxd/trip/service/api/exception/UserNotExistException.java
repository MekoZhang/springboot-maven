package cn.zhangxd.trip.service.api.exception;

import cn.zhangxd.trip.service.api.exception.base.BusinessException;

/**
 * 用户未存在
 * Created by zhangxd on 16/5/8.
 */
public class UserNotExistException extends BusinessException {

    public UserNotExistException(String message) {
        super(message);
    }

}
