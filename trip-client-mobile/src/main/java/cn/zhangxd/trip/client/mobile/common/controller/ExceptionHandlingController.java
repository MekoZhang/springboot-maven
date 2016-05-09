package cn.zhangxd.trip.client.mobile.common.controller;

import cn.zhangxd.trip.client.mobile.constant.MessageConstants;
import cn.zhangxd.trip.service.api.exception.base.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常解析 Controller
 * Created by zhangxd on 16/4/29.
 */
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> businessErrorHandler(BusinessException ex) {
        Map<String, Object> message = new HashMap<>();
        message.put(MessageConstants.RETURN_FIELD_CODE, ex.getCode());
        message.put(MessageConstants.RETURN_FIELD_MESSAGE, ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
