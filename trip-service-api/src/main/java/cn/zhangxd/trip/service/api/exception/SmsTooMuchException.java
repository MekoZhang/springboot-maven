package cn.zhangxd.trip.service.api.exception;

import cn.zhangxd.trip.service.api.exception.base.BusinessException;

/**
 * 短信发送太频繁
 * Created by zhangxd on 16/5/8.
 */
public class SmsTooMuchException extends BusinessException {

    public SmsTooMuchException(String message) {
        super(message);
    }

}
