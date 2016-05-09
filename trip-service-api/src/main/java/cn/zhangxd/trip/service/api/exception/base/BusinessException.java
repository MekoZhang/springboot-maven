package cn.zhangxd.trip.service.api.exception.base;

/**
 * 业务异常.
 * Created by zhangxd on 16/3/14.
 */
public class BusinessException extends Exception implements BaseException {

    private int code;

    public BusinessException() {
    }

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}