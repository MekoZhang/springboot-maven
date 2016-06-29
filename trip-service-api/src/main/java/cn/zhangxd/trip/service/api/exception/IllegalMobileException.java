package cn.zhangxd.trip.service.api.exception;

import cn.zhangxd.trip.service.api.exception.base.BusinessException;

/**
 * 手机号码不合法
 * Created by zhangxd on 16/5/8.
 */
public class IllegalMobileException extends BusinessException {

    public IllegalMobileException(String message) {
        super(message);
    }

}
