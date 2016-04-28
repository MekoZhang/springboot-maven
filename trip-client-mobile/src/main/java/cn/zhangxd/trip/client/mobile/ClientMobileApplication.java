package cn.zhangxd.trip.client.mobile;

import cn.zhangxd.trip.client.mobile.base.ServletContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@ImportResource("classpath:bubbo-consumer.xml")
public class ClientMobileApplication extends SpringBootServletInitializer {

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                ServletContextHolder.setServletContext(servletContext);
            }
        };
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ClientMobileApplication.class); // 以 war 包形式发布时需要此设置
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientMobileApplication.class, args);
    }
}
