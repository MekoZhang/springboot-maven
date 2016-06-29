package cn.zhangxd.trip.client.mobile.base;

import cn.zhangxd.trip.client.mobile.base.converter.JsonMapper;
import cn.zhangxd.trip.client.mobile.base.converter.MyBeanSerializerModifier;
import cn.zhangxd.trip.client.mobile.common.filter.UserAgentFilter;
import cn.zhangxd.trip.client.mobile.common.interceptor.LogInterceptor;
import cn.zhangxd.trip.client.mobile.security.exception.OAuth2ExceptionMixIn;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WEB配置类
 * Created by zhangxd on 16/3/10.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public UserAgentFilter userAgentFilter() {
        return new UserAgentFilter();
    }

    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置日志拦截器拦截请求路径
        registry
                .addInterceptor(logInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
        ;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new JsonMapper();
        mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));
        mapper.addMixIn(OAuth2Exception.class, OAuth2ExceptionMixIn.class);
        return mapper;
    }

    @Bean
    public HttpMessageConverter  httpMessageConverter() {
        return new MappingJackson2HttpMessageConverter(this.objectMapper());
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false) // 系统对外暴露的 URL 不会识别和匹配 .* 后缀
                .setUseTrailingSlashMatch(true); // 系统不区分 URL 的最后一个字符是否是斜杠 /
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        // 等价于<mvc:default-servlet-handler />, 对静态资源文件的访问, 将无法 mapping 到 Controller 的 path 交给 default servlet handler 处理
        configurer.enable();
    }

    @Bean
    public MultipartResolver getMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(5 * 1024 * 1024); // 5MB

        return resolver;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MethodValidationPostProcessor getMethodValidationPostProcessor(){
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }
}
