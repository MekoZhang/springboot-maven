package cn.zhangxd.trip.client.mobile.common.controller;

import cn.zhangxd.trip.client.mobile.constant.Message;
import cn.zhangxd.trip.client.mobile.constant.ReturnCode;
import cn.zhangxd.trip.service.api.exception.base.BusinessException;
import cn.zhangxd.trip.util.BeanValidators;
import cn.zhangxd.trip.util.Collections3;
import cn.zhangxd.trip.util.DateHelper;
import cn.zhangxd.trip.util.EncodeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器支持类
 * Created by zhangxd on 16/3/10.
 */
public abstract class BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : EncodeHelper.escapeHtml(text.trim()));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateHelper.parseDate(text));
            }
        });
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBusinessException(BusinessException ex) {
        return makeErrorMessage(ReturnCode.BAD_REQUEST, "Business Error", ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> list = BeanValidators.extractMessage(ex);
        return makeErrorMessage(ReturnCode.INVALID_FIELD,
                "Invalid Field", Collections3.convertToString(list, ";"));
    }

    protected Map<String, Object> makeErrorMessage(String code, String error, String desc) {
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, code);
        message.put(Message.RETURN_FIELD_ERROR, error);
        message.put(Message.RETURN_FIELD_ERROR_DESC, desc);
        return message;
    }

}
