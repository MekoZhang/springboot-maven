package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.client.mobile.common.controller.BaseController;
import cn.zhangxd.trip.client.mobile.constant.Message;
import cn.zhangxd.trip.client.mobile.constant.ReturnCode;
import cn.zhangxd.trip.service.api.service.TripUserService;
import cn.zhangxd.trip.service.api.vo.TripUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController extends BaseController {
	
    @Autowired
	private TripUserService tripUserService;
	
	@RequestMapping("/hello")
	public Map<String, Object> hello(String name) throws Exception{
        Map<String, Object> message = new HashMap<>();
        TripUser user = tripUserService.findUserByLogin(name);
		logger.info("==========" +  (tripUserService == null) + user.toString());
        message.put(Message.RETURN_FIELD_CODE, ReturnCode.CODE_SUCCESS);
        message.put(Message.RETURN_FIELD_DATA, user);
        return message;
	}

}
