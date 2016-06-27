package cn.zhangxd.trip.service.api.exception;

import cn.zhangxd.trip.service.api.exception.base.BusinessException;

/**
 * 用户未发现
 * Created by zhangxd on 16/5/8.
 */
public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
