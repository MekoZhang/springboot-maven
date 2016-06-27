package cn.zhangxd.trip.client.mobile.constant;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据常量
 * Created by zhangxd on 16/5/9.
 */
public class Message {

    /**
     * 状态
     */
    public static final String RETURN_FIELD_CODE = "code";
    /**
     * 错误信息
     */
    public static final String RETURN_FIELD_ERROR = "error";
    /**
     * 错误描述
     */
    public static final String RETURN_FIELD_ERROR_DESC = "error_description";
    /**
     * 返回数据
     */
    public static final String RETURN_FIELD_DATA = "data";

    /**
     * HTTP状态码与系统错误代码对应关系
     */
    public static final Map<Integer, String> STATUS_CODE_MAP = new HashMap<>();

    static {
        STATUS_CODE_MAP.put(HttpStatus.SC_INTERNAL_SERVER_ERROR, ReturnCode.INTERNAL_SERVER_ERROR);
        STATUS_CODE_MAP.put(HttpStatus.SC_METHOD_NOT_ALLOWED, ReturnCode.METHOD_NOT_ALLOWED);
        STATUS_CODE_MAP.put(HttpStatus.SC_BAD_REQUEST, ReturnCode.BAD_REQUEST);
        STATUS_CODE_MAP.put(HttpStatus.SC_NOT_FOUND, ReturnCode.NOT_FOUND);
        STATUS_CODE_MAP.put(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, ReturnCode.UNSUPPORTED_MEDIA_TYPE);
        STATUS_CODE_MAP.put(HttpStatus.SC_NOT_ACCEPTABLE, ReturnCode.NOT_ACCEPTABLE);
        STATUS_CODE_MAP.put(HttpStatus.SC_UNAUTHORIZED, ReturnCode.UNAUTHORIZED);
        STATUS_CODE_MAP.put(HttpStatus.SC_FORBIDDEN, ReturnCode.FORBIDDEN);
    }

}
