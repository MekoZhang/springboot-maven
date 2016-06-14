package cn.zhangxd.trip.client.mobile.common.controller;

import cn.zhangxd.trip.client.mobile.constant.Message;
import cn.zhangxd.trip.service.api.exception.base.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常解析 Controller
 * Created by zhangxd on 16/4/29.
 */
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getHttpErrorCode());
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, ex.getBusinessErrorCode());
        message.put(Message.RETURN_FIELD_ERROR, httpStatus.getReasonPhrase());
        message.put(Message.RETURN_FIELD_ERROR_DESC, ex.getMessage());
        return new ResponseEntity<>(message, httpStatus);
    }

}
