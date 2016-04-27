package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.service.api.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
    @Reference
	private UserService userService;
	
	@RequestMapping("/hello")
	public String hello(String name) {
		System.out.println("==========" +  (userService == null) + userService.findUser().size());
		return "Hello: " + name;
	}

}
