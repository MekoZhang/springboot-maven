package cn.zhangxd.trip.client.mobile.constant;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据常量
 * Created by zhangxd on 16/5/9.
 */
public class MessageConstants {

    /** 状态 */
    public static final String RETURN_FIELD_CODE			= "code";
    /** 错误信息 */
    public static final String RETURN_FIELD_MESSAGE			= "message";
    /** 返回数据 */
    public static final String RETURN_FIELD_DATA			= "data";

    /**
     * HTTP状态码与系统错误代码对应关系
     */
    public static final Map<Integer, Integer> STATUS_CODE_MAP = new HashMap<>();

    static {
        STATUS_CODE_MAP.put(HttpStatus.SC_INTERNAL_SERVER_ERROR, ReturnCodeConstants.CODE_INTERNAL_SERVER_ERROR);
        STATUS_CODE_MAP.put(HttpStatus.SC_METHOD_NOT_ALLOWED, ReturnCodeConstants.CODE_METHOD_NOT_ALLOWED);
        STATUS_CODE_MAP.put(HttpStatus.SC_BAD_REQUEST, ReturnCodeConstants.CODE_BAD_REQUEST);
        STATUS_CODE_MAP.put(HttpStatus.SC_FORBIDDEN, ReturnCodeConstants.CODE_FORBIDDEN);
    }

}
