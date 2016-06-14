package cn.zhangxd.trip.service.api.exception.base;

/**
 * 业务异常.
 * Created by zhangxd on 16/3/14.
 */
public class BusinessException extends Exception {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public String getBusinessErrorCode() {
        return "40001";
    }

    public int getHttpErrorCode() {
        return 400;
    }
}