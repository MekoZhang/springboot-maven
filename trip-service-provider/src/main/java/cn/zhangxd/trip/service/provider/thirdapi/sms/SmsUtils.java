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

/**
 * 短信工具类
 * Created by zhangxd on 16/6/29.
 */
public class SmsUtils {

    private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);

    private static final String BUSINESS_LIMIT_CONTROL = "isv.BUSINESS_LIMIT_CONTROL";
    private static final String MOBILE_NUMBER_ILLEGAL = "isv.MOBILE_NUMBER_ILLEGAL";

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
    public static void sendMessage(
            String extend, String signName, String paramString,
            String recNum, String templateCode
    ) throws SmsTooMuchException, IllegalMobileException {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23393509", "fbee7a46d4fa2341e0baec53c0d027ef");
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

}
