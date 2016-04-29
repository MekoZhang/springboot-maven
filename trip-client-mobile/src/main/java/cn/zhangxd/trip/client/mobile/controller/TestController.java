package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.client.mobile.common.controller.BaseController;
import cn.zhangxd.trip.service.api.service.TripUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController {
	
    @Autowired
	private TripUserService tripUserService;
	
	@RequestMapping("/hello")
	public String hello(String name) {
		System.out.println("==========" +  (tripUserService == null) + tripUserService.findUserByLogin(name).toString());
		return "Hello: " + name;
	}

}
