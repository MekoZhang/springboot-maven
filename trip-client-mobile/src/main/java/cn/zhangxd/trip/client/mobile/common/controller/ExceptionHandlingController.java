package cn.zhangxd.trip.client.mobile.common.controller;

import cn.zhangxd.trip.client.mobile.common.message.Message;
import cn.zhangxd.trip.service.api.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * 异常解析 Controller
 * Created by zhangxd on 16/4/29.
 */
@ControllerAdvice
@PropertySource("classpath:message.properties")
public class ExceptionHandlingController {

    @Autowired
    private Environment env;

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Message businessErrorHandler(BusinessException ex) {
        return new Message(ex.getCode(), env.getProperty(Objects.toString(ex.getCode())));
    }

}
