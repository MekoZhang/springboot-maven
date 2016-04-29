package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.client.mobile.common.controller.BaseController;
import cn.zhangxd.trip.client.mobile.common.message.Message;
import cn.zhangxd.trip.service.api.exception.BusinessException;
import cn.zhangxd.trip.service.api.service.TripUserService;
import cn.zhangxd.trip.service.api.vo.TripUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController {
	
    @Autowired
	private TripUserService tripUserService;
	
	@RequestMapping("/hello")
	public Message hello(String name) throws Exception{
        Message message = new Message();
        TripUser user = tripUserService.findUserByLogin(name);
        if (1==1) throw new BusinessException(600);
		System.out.println("==========" +  (tripUserService == null) + user.toString());
        message.setMsg(user);
        return message;
	}

}
