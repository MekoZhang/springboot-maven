package cn.zhangxd.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

/**
 * Spring-boot 启动入口
 * Created by zhangxd on 16/3/7.
 */
@SpringBootApplication
@ImportResource("classpath:dubbo-provider.xml")
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(Application.class);
        application.setRegisterShutdownHook(false);
        application.run(args);
        System.in.read(); // 按任意键退出
    }
}
