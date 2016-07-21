package cn.zhangxd.trip.service.api.exception;

import cn.zhangxd.trip.service.api.exception.base.BusinessException;

/**
 * 坐标解析失败
 * Created by zhangxd on 16/5/8.
 */
public class ReverseGeoException extends BusinessException {

    public ReverseGeoException(String message) {
        super(message);
    }

}
