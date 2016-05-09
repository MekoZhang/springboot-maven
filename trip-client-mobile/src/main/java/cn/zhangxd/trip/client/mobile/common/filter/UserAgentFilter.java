package cn.zhangxd.trip.client.mobile.common.filter;

import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用户信息解析
 * Created by zhangxd on 16/5/9.
 */
public class UserAgentFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(UserAgentFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest hrq = (HttpServletRequest) request;
            String aa = hrq.getHeader(HTTP.USER_AGENT);
            logger.debug(aa);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
