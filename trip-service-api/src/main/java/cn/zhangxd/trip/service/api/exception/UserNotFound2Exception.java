package cn.zhangxd.trip.service.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 用户未发现
 * Created by zhangxd on 16/5/8.
 */
@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "用户未发现")
public class UserNotFound2Exception extends RuntimeException {

    public UserNotFound2Exception(String message) {
        super(message);
    }
}
