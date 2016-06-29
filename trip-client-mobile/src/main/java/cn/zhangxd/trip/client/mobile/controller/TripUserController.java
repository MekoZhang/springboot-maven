package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.client.mobile.common.controller.BaseController;
import cn.zhangxd.trip.client.mobile.constant.Message;
import cn.zhangxd.trip.client.mobile.constant.ReturnCode;
import cn.zhangxd.trip.service.api.entity.TripUser;
import cn.zhangxd.trip.service.api.exception.InvalidCaptchaException;
import cn.zhangxd.trip.service.api.service.ICaptchaService;
import cn.zhangxd.trip.service.api.service.ITripUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/{version}/users")
public class TripUserController extends BaseController {

    @Autowired
    private ITripUserService tripUserService;
    @Autowired
    private ICaptchaService captchaService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> registryUser(
            @PathVariable("version") String version,
            @RequestParam("nickname") String nickname,
            @RequestParam("password") String password,
            @RequestParam("mobile") String mobile,
            @RequestParam("captcha") String captcha
    ) throws Exception {

        if (captchaService.validCaptcha(mobile, captcha)) {
            TripUser user = new TripUser();
            user.setNickname(nickname);
            user.setMobile(mobile);
            user.setPassword(passwordEncoder.encode(password));
            // 注册
            tripUserService.save(user);
        }

        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
        return message;
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public Map<String, Object> forgetPassword(
            @PathVariable("version") String version,
            @RequestParam("password") String password,
            @RequestParam("mobile") String mobile,
            @RequestParam("captcha") String captcha
    ) throws Exception {

        if (captchaService.validCaptcha(mobile, captcha)) {
            TripUser user = new TripUser();
            user.setMobile(mobile);
            user.setPassword(passwordEncoder.encode(password));
            // 忘记密码
            tripUserService.updatePasswordByMobile(user);
        }

        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
        return message;
    }

    @ExceptionHandler(InvalidCaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleSmsTooMuchException(InvalidCaptchaException ex) {
        return makeErrorMessage(ReturnCode.INVALID_CAPTCHA, "Invalid Captcha", ex.getMessage());
    }

}
