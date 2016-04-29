package cn.zhangxd.trip.client.mobile.common.interceptor;

import cn.zhangxd.trip.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

/**
 * 日志拦截器
 * Created by zhangxd on 15/10/20.
 */
@Configuration
public class LogInterceptor extends HandlerInterceptorAdapter {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (logger.isDebugEnabled()) {
            String contentType = request.getContentType();
            String reqData;
            if ("application/json".equalsIgnoreCase(contentType)) {
                int contentLength = request.getContentLength();
                byte buffer[] = new byte[contentLength];
                for (int i = 0; i < contentLength; ) {
                    int length = request.getInputStream().read(buffer, i, contentLength - i);
                    if (length == -1) {
                        break;
                    }
                    i += length;
                }
                reqData = new String(buffer, DEFAULT_URL_ENCODING);
            } else {
                StringBuilder sb = new StringBuilder();
                // 必须这么些，其他方式遍历不出来
                for (Object key : request.getParameterMap().keySet()) {
                    sb.append(key.toString()).append("=")
                            .append(ServletRequestUtils.getStringParameter(request, key.toString(), "")).append(";");
                }
                reqData = sb.toString();
            }

            Enumeration e = request.getHeaderNames();
            String reqHeader = "";
            StringBuilder sb = new StringBuilder();
            while (e.hasMoreElements()) {
                String headValue = (String) e.nextElement();
                String value = request.getHeader(headValue);
                sb.append(headValue).append("=").append(value).append(";");
                reqHeader = sb.toString();
            }

            long beginTime = System.currentTimeMillis();//1、开始时间
            startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
            logger.debug("开始计时: {}  URI: {} 请求参数: {} 请求头: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI(), reqData, reqHeader);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            logger.info("ViewName: " + modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        // 打印JVM信息。
        if (logger.isDebugEnabled()) {
            long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
            long endTime = System.currentTimeMillis();    //2、结束时间

            logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateHelper.formatDateTime(endTime - beginTime),
                    request.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }

    }

}
