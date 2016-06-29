package cn.zhangxd.trip.service.api.service;

import cn.zhangxd.trip.service.api.exception.*;

/**
 * 短信服务
 * Created by zhangxd on 16/4/18.
 */
public interface ICaptchaService {

    /**
     * 注册发送验证码
     */
    void sendCaptcha4Registry(String mobile) throws UserExistException, SmsTooMuchException, IllegalMobileException;

    /**
     * 忘记密码发送验证码
     */
    void sendCaptcha4Forget(String mobile) throws UserNotExistException, SmsTooMuchException, IllegalMobileException;

    /**
     * 验证验证码是否正确
     */
    boolean validCaptcha(String mobile, String captcha) throws InvalidCaptchaException;

}
