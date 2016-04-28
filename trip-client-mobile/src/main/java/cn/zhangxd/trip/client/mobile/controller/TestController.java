package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.client.mobile.common.controller.BaseController;
import cn.zhangxd.trip.service.api.service.TripUserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController {
	
    @Reference
	private TripUserService tripUserService;
	
	@RequestMapping("/hello")
	public String hello(String name) throws Exception {
		System.out.println("==========" +  (tripUserService == null) + tripUserService.findUserByLogin(name));
		return "Hello: " + name;
	}

}
