package cn.zhangxd.trip.client.mobile.common.controller;

import cn.zhangxd.trip.client.mobile.common.message.Message;
import cn.zhangxd.trip.service.api.exception.base.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常解析 Controller
 * Created by zhangxd on 16/4/29.
 */
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<Message> businessErrorHandler(BusinessException ex) {
        Message message = new Message(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
