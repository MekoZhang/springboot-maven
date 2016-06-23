package cn.zhangxd.trip.web.admin;

import cn.zhangxd.trip.web.admin.base.ServletContextHolder;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@RestController
@ImportResource("classpath:bubbo-consumer.xml")
public class WebAdminApplication extends SpringBootServletInitializer {

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
        builder.bannerMode(Banner.Mode.OFF);
        return builder.sources(WebAdminApplication.class); // 以 war 包形式发布时需要此设置
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WebAdminApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
