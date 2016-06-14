package cn.zhangxd.trip.client.mobile.constant;

/**
 * 返回 code 常量
 * Created by zhangxd on 16/5/9.
 */
public class ReturnCode {

    /**
     * 成功
     */
    public static final String CODE_SUCCESS = "0";
    /**
     * 400 (错误请求) 服务器不理解请求的语法。
     */
    public static final String CODE_BAD_REQUEST = "40001";
    /**
     * 400 无效 Grant Type
     */
    public static final String CODE_INVALID_GRANT = "40002";
    /**
     * 400 无效 Scope
     */
    public static final String CODE_INVALID_SCOPE = "40003";
    /**
     * 400 无效 Request
     */
    public static final String CODE_INVALID_REQUEST = "40004";
    /**
     * 400 不支持的 Grant Type
     */
    public static final String CODE_UNSUPPORTED_GRANT_TYPE = "40005";
    /**
     * 400 不支持的 Response Type
     */
    public static final String CODE_UNSUPPORTED_RESPONSE_TYPE = "40006";
    /**
     * 401 (未授权) 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
     */
    public static final String CODE_UNAUTHORIZED = "40101";
    /**
     * 401 客户端不可用
     */
    public static final String CODE_INVALID_CLIENT = "40102";
    /**
     * 401 客户端未授权
     */
    public static final String CODE_UNAUTHORIZED_CLIENT = "40103";
    /**
     * 401 Token 无效
     */
    public static final String CODE_INVALID_TOKEN = "40104";
    /**
     * 403 (禁止) 服务器拒绝请求。
     */
    public static final String CODE_FORBIDDEN = "40301";
    /**
     * 404 (未找到) 服务器找不到请求的资源。
     */
    public static final String CODE_NOT_FOUND = "40401";
    /**
     * 405 (方法禁用) 禁用请求中指定的方法。
     */
    public static final String CODE_METHOD_NOT_ALLOWED = "40501";
    /**
     * 406 (不接受) 无法使用请求的内容特性响应请求的网页。
     */
    public static final String CODE_NOT_ACCEPTABLE = "40601";
    /**
     * 407 (需要代理授权) 此状态代码与 401(未授权)类似，但指定请求者应当授权使用代理。
     */
    public static final String CODE_PROXY_AUTHENTICATION_REQUIRED = "40701";
    /**
     * 408 (请求超时) 服务器等候请求时发生超时。
     */
    public static final String CODE_REQUEST_TIMEOUT = "40801";
    /**
     * 409 (冲突) 服务器在完成请求时发生冲突。服务器必须在响应中包含有关冲突的信息。
     */
    public static final String CODE_CONFLICT = "40901";
    /**
     * 410 (已删除) 如果请求的资源已永久删除，服务器就会返回此响应。
     */
    public static final String CODE_GONE = "41001";
    /**
     * 411 (需要有效长度) 服务器不接受不含有效内容长度标头字段的请求。
     */
    public static final String CODE_LENGTH_REQUIRED = "41101";
    /**
     * 412 (未满足前提条件) 服务器未满足请求者在请求中设置的其中一个前提条件。
     */
    public static final String CODE_PRECONDITION_FAILED = "41201";
    /**
     * 413 (请求实体过大) 服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。
     */
    public static final String CODE_REQUEST_TOO_LONG = "41301";
    /**
     * 414 (请求的 URI 过长) 请求的 URI(通常为网址)过长，服务器无法处理。
     */
    public static final String CODE_REQUEST_URI_TOO_LONG = "41401";
    /**
     * 415 (不支持的媒体类型) 请求的格式不受请求页面的支持。
     */
    public static final String CODE_UNSUPPORTED_MEDIA_TYPE = "41501";
    /**
     * 416 (请求范围不符合要求) 如果页面无法提供请求的范围，则服务器会返回此状态代码。
     */
    public static final String CODE_REQUESTED_RANGE_NOT_SATISFIABLE = "41601";
    /**
     * 417 (未满足期望值) 服务器未满足"期望"请求标头字段的要求。
     */
    public static final String CODE_EXPECTATION_FAILED = "41701";
    /**
     * 500 (服务器内部错误) 服务器遇到错误，无法完成请求。
     */
    public static final String CODE_INTERNAL_SERVER_ERROR = "50001";
    /**
     * 501 (尚未实施) 服务器不具备完成请求的功能。例如，服务器无法识别请求方法时可能会返回此代码。
     */
    public static final String CODE_NOT_IMPLEMENTED = "50101";
    /**
     * 502 (错误网关) 服务器作为网关或代理，从上游服务器收到无效响应。
     */
    public static final String CODE_BAD_GATEWAY = "50201";
    /**
     * 503 (服务不可用) 服务器目前无法使用(由于超载或停机维护)。通常，这只是暂时状态。
     */
    public static final String CODE_SERVICE_UNAVAILABLE = "50301";
    /**
     * 504 (网关超时) 服务器作为网关或代理，但是没有及时从上游服务器收到请求。
     */
    public static final String CODE_GATEWAY_TIMEOUT = "50401";
    /**
     * 505 (HTTP 版本不受支持) 服务器不支持请求中所用的 HTTP 协议版本。
     */
    public static final String CODE_HTTP_VERSION_NOT_SUPPORTED = "50501";

}
