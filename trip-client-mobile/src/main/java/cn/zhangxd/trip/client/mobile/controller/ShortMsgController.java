package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.client.mobile.common.controller.BaseController;
import cn.zhangxd.trip.client.mobile.constant.Message;
import cn.zhangxd.trip.client.mobile.constant.ReturnCode;
import cn.zhangxd.trip.service.api.exception.IllegalMobileException;
import cn.zhangxd.trip.service.api.exception.SmsTooMuchException;
import cn.zhangxd.trip.service.api.exception.UserExistException;
import cn.zhangxd.trip.service.api.exception.UserNotExistException;
import cn.zhangxd.trip.service.api.exception.base.BusinessException;
import cn.zhangxd.trip.service.api.service.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/{version}/sms")
public class ShortMsgController extends BaseController {

    private static final String TYPE_REGISTRY = "registry";
    private static final String TYPE_FORGET = "forget";

    @Autowired
    private ICaptchaService captchaService;

    @RequestMapping(value = "/captcha", method = RequestMethod.POST)
    public Map<String, Object> sendCaptcha(
            @PathVariable("version") String version,
            @RequestParam("type") String type,
            @RequestParam("mobile") String mobile
    ) throws Exception {
        if (TYPE_REGISTRY.equals(type)) {
            captchaService.sendCaptcha4Registry(mobile);
        } else if (TYPE_FORGET.equals(type)) {
            captchaService.sendCaptcha4Forget(mobile);
        } else {
            throw new UnexpectedTypeException(String.format("无效的类型: %s", type));
        }
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
        return message;
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleUnexpectedTypeException(UnexpectedTypeException ex) {
        return makeErrorMessage(ReturnCode.INVALID_FIELD, "Type Error", ex.getMessage());
    }

    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleUserExistException(UserExistException ex) {
        return makeErrorMessage(ReturnCode.USER_EXIST, "User Exist", ex.getMessage());
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
        return makeErrorMessage(ReturnCode.USER_NOT_EXIST, "User Not Exist", ex.getMessage());
    }

    @ExceptionHandler(SmsTooMuchException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handleSmsTooMuchException(SmsTooMuchException ex) {
        return makeErrorMessage(ReturnCode.SMS_TOO_MUCH, "SMS Too Much", ex.getMessage());
    }

    @ExceptionHandler(IllegalMobileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleIllegalMobileException(IllegalMobileException ex) {
        return makeErrorMessage(ReturnCode.INVALID_FIELD, "Illegal Mobile", ex.getMessage());
    }

    private static class UnexpectedTypeException extends BusinessException {

        public UnexpectedTypeException(String message) {
            super(message);

        }
    }
}
