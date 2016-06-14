package cn.zhangxd.trip.client.mobile.constant;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据常量
 * Created by zhangxd on 16/5/9.
 */
public class Message {

    /** 状态 */
    public static final String RETURN_FIELD_CODE			= "code";
    /** 错误信息 */
    public static final String RETURN_FIELD_ERROR           = "error";
    /** 错误描述 */
    public static final String RETURN_FIELD_ERROR_DESC      = "error_description";
    /** 返回数据 */
    public static final String RETURN_FIELD_DATA			= "data";

    /**
     * HTTP状态码与系统错误代码对应关系
     */
    public static final Map<Integer, String> STATUS_CODE_MAP = new HashMap<>();

    static {
        STATUS_CODE_MAP.put(HttpStatus.SC_INTERNAL_SERVER_ERROR, ReturnCode.CODE_INTERNAL_SERVER_ERROR);
        STATUS_CODE_MAP.put(HttpStatus.SC_METHOD_NOT_ALLOWED, ReturnCode.CODE_METHOD_NOT_ALLOWED);
        STATUS_CODE_MAP.put(HttpStatus.SC_BAD_REQUEST, ReturnCode.CODE_BAD_REQUEST);
        STATUS_CODE_MAP.put(HttpStatus.SC_FORBIDDEN, ReturnCode.CODE_FORBIDDEN);
        STATUS_CODE_MAP.put(HttpStatus.SC_NOT_FOUND, ReturnCode.CODE_NOT_FOUND);
        STATUS_CODE_MAP.put(HttpStatus.SC_BAD_REQUEST, ReturnCode.CODE_BAD_REQUEST);
        STATUS_CODE_MAP.put(HttpStatus.SC_UNAUTHORIZED, ReturnCode.CODE_UNAUTHORIZED);
        STATUS_CODE_MAP.put(HttpStatus.SC_FORBIDDEN, ReturnCode.CODE_FORBIDDEN);
        STATUS_CODE_MAP.put(HttpStatus.SC_NOT_FOUND, ReturnCode.CODE_NOT_FOUND);
        STATUS_CODE_MAP.put(HttpStatus.SC_METHOD_NOT_ALLOWED, ReturnCode.CODE_METHOD_NOT_ALLOWED);
        STATUS_CODE_MAP.put(HttpStatus.SC_NOT_ACCEPTABLE, ReturnCode.CODE_NOT_ACCEPTABLE);
        STATUS_CODE_MAP.put(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, ReturnCode.CODE_PROXY_AUTHENTICATION_REQUIRED);
        STATUS_CODE_MAP.put(HttpStatus.SC_REQUEST_TIMEOUT, ReturnCode.CODE_REQUEST_TIMEOUT);
        STATUS_CODE_MAP.put(HttpStatus.SC_CONFLICT, ReturnCode.CODE_CONFLICT);
        STATUS_CODE_MAP.put(HttpStatus.SC_GONE, ReturnCode.CODE_GONE);
        STATUS_CODE_MAP.put(HttpStatus.SC_LENGTH_REQUIRED, ReturnCode.CODE_LENGTH_REQUIRED);
        STATUS_CODE_MAP.put(HttpStatus.SC_PRECONDITION_FAILED, ReturnCode.CODE_PRECONDITION_FAILED);
        STATUS_CODE_MAP.put(HttpStatus.SC_REQUEST_TOO_LONG, ReturnCode.CODE_REQUEST_TOO_LONG);
        STATUS_CODE_MAP.put(HttpStatus.SC_REQUEST_URI_TOO_LONG, ReturnCode.CODE_REQUEST_URI_TOO_LONG);
        STATUS_CODE_MAP.put(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, ReturnCode.CODE_UNSUPPORTED_MEDIA_TYPE);
        STATUS_CODE_MAP.put(HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE, ReturnCode.CODE_REQUESTED_RANGE_NOT_SATISFIABLE);
        STATUS_CODE_MAP.put(HttpStatus.SC_EXPECTATION_FAILED, ReturnCode.CODE_EXPECTATION_FAILED);
        STATUS_CODE_MAP.put(HttpStatus.SC_INTERNAL_SERVER_ERROR, ReturnCode.CODE_INTERNAL_SERVER_ERROR);
        STATUS_CODE_MAP.put(HttpStatus.SC_NOT_IMPLEMENTED, ReturnCode.CODE_NOT_IMPLEMENTED);
        STATUS_CODE_MAP.put(HttpStatus.SC_BAD_GATEWAY, ReturnCode.CODE_BAD_GATEWAY);
        STATUS_CODE_MAP.put(HttpStatus.SC_SERVICE_UNAVAILABLE, ReturnCode.CODE_SERVICE_UNAVAILABLE);
        STATUS_CODE_MAP.put(HttpStatus.SC_GATEWAY_TIMEOUT, ReturnCode.CODE_GATEWAY_TIMEOUT);
        STATUS_CODE_MAP.put(HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED, ReturnCode.CODE_HTTP_VERSION_NOT_SUPPORTED);

    }

}
