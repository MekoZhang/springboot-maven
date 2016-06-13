package cn.zhangxd.trip.client.mobile.common.controller;

import cn.zhangxd.trip.client.mobile.constant.MessageConstants;
import cn.zhangxd.trip.client.mobile.constant.ReturnCodeConstants;
import cn.zhangxd.trip.service.api.exception.UserNotFoundException;
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
        message.put(MessageConstants.RETURN_FIELD_CODE, ReturnCodeConstants.CODE_INTERNAL_SERVER_ERROR);
        message.put(MessageConstants.RETURN_FIELD_MESSAGE, ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> userNotFoundErrorHandler(UserNotFoundException ex) {
        Map<String, Object> message = new HashMap<>();
        message.put(MessageConstants.RETURN_FIELD_CODE, ReturnCodeConstants.CODE_BAD_REQUEST); //TODO 指定代码
        message.put(MessageConstants.RETURN_FIELD_MESSAGE, ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
