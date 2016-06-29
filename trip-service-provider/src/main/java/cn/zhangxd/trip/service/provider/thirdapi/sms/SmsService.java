package cn.zhangxd.trip.service.provider.thirdapi.sms;

import cn.zhangxd.trip.service.api.exception.IllegalMobileException;
import cn.zhangxd.trip.service.api.exception.SmsTooMuchException;
import cn.zhangxd.trip.util.StringHelper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * 短信工具类
 * Created by zhangxd on 16/6/29.
 */
@Service
@EnableConfigurationProperties
@ConfigurationProperties("api.taobao.sms")
public class SmsService {

    private static Logger logger = LoggerFactory.getLogger(SmsService.class);

    private static final String BUSINESS_LIMIT_CONTROL = "isv.BUSINESS_LIMIT_CONTROL";
    private static final String MOBILE_NUMBER_ILLEGAL = "isv.MOBILE_NUMBER_ILLEGAL";

    private String url;
    private String key;
    private String secret;

    /**
     * 短信发送
     *
     * @param extend       回传参数(可选)
     * @param signName     短信签名
     * @param paramString  模板变量(可选)
     * @param recNum       接受号码,最多200个
     * @param templateCode 模板编号
     * @throws SmsTooMuchException
     * @throws IllegalMobileException
     */
    public void sendMessage(
            String extend, String signName, String paramString,
            String recNum, String templateCode
    ) throws SmsTooMuchException, IllegalMobileException {
        TaobaoClient client = new DefaultTaobaoClient(url, key, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        if (!StringHelper.isEmpty(extend)) {
            req.setExtend(extend);
        }
        req.setSmsType("normal");
        req.setSmsFreeSignName(signName);
        if (!StringHelper.isEmpty(paramString)) {
            req.setSmsParamString(paramString);
        }
        req.setRecNum(recNum);
        req.setSmsTemplateCode(templateCode);
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            logger.info(rsp.getBody());
            if (BUSINESS_LIMIT_CONTROL.equals(rsp.getSubCode())) {
                throw new SmsTooMuchException("短信发送太频繁");
            } else if (MOBILE_NUMBER_ILLEGAL.equals(rsp.getSubCode())) {
                throw new IllegalMobileException(String.format("手机号码 '%s' 格式错误", recNum));
            }
        } catch (ApiException e) {
            logger.error("短信服务异常", e);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
