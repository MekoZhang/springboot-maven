package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.client.mobile.common.controller.BaseController;
import cn.zhangxd.trip.client.mobile.constant.Message;
import cn.zhangxd.trip.client.mobile.constant.ReturnCode;
import cn.zhangxd.trip.service.api.entity.TripUser;
import cn.zhangxd.trip.service.api.service.ITripUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController extends BaseController {
	
    @Autowired
	private ITripUserService tripUserService;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> hello(@RequestParam(value = "name", required = true) String name) throws Exception{
        Map<String, Object> message = new HashMap<>();
        TripUser user = tripUserService.findUserByLogin(name);
		logger.info("==========" +  (tripUserService == null) + user.toString());
        message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
        message.put(Message.RETURN_FIELD_DATA, user);
        return message;
	}

}
