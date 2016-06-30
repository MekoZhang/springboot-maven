package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.repo.RedisRepo;
import cn.zhangxd.trip.service.api.entity.TripUser;
import cn.zhangxd.trip.service.api.exception.*;
import cn.zhangxd.trip.service.api.service.ICaptchaService;
import cn.zhangxd.trip.service.api.service.ITripUserService;
import cn.zhangxd.trip.service.provider.common.service.BaseService;
import cn.zhangxd.trip.service.provider.thirdapi.sms.SmsApiService;
import cn.zhangxd.trip.util.RandomHelper;
import cn.zhangxd.trip.util.StringHelper;
import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service(protocol = {"dubbo"}, timeout = 5000)
@Transactional(readOnly = true)
public class CaptchaService extends BaseService implements ICaptchaService {

    @Autowired
    private SmsApiService smsService;
    @Autowired
    private ITripUserService tripUserService;
    @Autowired
    private RedisRepo redisRepo;

    private static final String REDIS_PREFIX = "captcha_";

    @Override
    public void sendCaptcha4Registry(String mobile) throws UserExistException, SmsTooMuchException, IllegalMobileException {
        TripUser user = tripUserService.findUserByMobile(mobile);
        // 注册,如果用户存在
        if (user != null) {
            throw new UserExistException(String.format("手机号 '%s' 已经注册,直接登录", mobile));
        }

        sendCaptcha(mobile);
    }

    @Override
    public void sendCaptcha4Forget(String mobile) throws UserNotExistException, SmsTooMuchException, IllegalMobileException {
        TripUser user = tripUserService.findUserByMobile(mobile);
        // 忘记密码,如果用户不存在
        if (user == null) {
            throw new UserNotExistException(String.format(" 手机号 '%s' 未注册", mobile));
        }

        sendCaptcha(mobile);
    }

    @Override
    public boolean validCaptcha(String mobile, String captcha) throws InvalidCaptchaException {
        String code = "";
        try {
            code = redisRepo.get(REDIS_PREFIX + mobile);
        } catch (Exception e) {
            logger.error("Redis异常", e);
        }
        if (StringHelper.isEmpty(code) || !code.equals(captcha)) {
            throw new InvalidCaptchaException(String.format("验证码 '%s' 无效", captcha));
        }
        return true;
    }

    private void sendCaptcha(String mobile) throws SmsTooMuchException, IllegalMobileException {
        String signName = "环球旅行测试";
        String templateCode = "SMS_11055175";
        String code = RandomHelper.getRandNum(4);

        //存入 Redis
        try {
            redisRepo.setExpire(REDIS_PREFIX + mobile, code, 600); //10分钟有效期
        } catch (Exception e) {
            logger.error("Redis异常", e);
        }

        Map<String, Object> smsParam = new HashMap<>();
        smsParam.put("code", code);
        smsParam.put("product", "环球旅行");
        String smsParamString = new Gson().toJson(smsParam);

        smsService.sendMessage("", signName, smsParamString, mobile, templateCode);
    }

}
