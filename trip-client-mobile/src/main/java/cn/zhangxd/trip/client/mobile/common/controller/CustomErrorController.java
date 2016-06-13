package cn.zhangxd.trip.client.mobile.common.controller;

import cn.zhangxd.trip.client.mobile.constant.MessageConstants;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ERROR处理控制器
 * Created by zhangxd on 16/3/14.
 */
@Controller
@RequestMapping("/error")
public class CustomErrorController extends BasicErrorController {

    public CustomErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @RequestMapping
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        Map<String, Object> message = new HashMap<>();
        message.put(MessageConstants.RETURN_FIELD_CODE, MessageConstants.STATUS_CODE_MAP.get(status.value()));
        message.put(MessageConstants.RETURN_FIELD_MESSAGE, status.getReasonPhrase());
        return new ResponseEntity<>(message, status);
    }

}
