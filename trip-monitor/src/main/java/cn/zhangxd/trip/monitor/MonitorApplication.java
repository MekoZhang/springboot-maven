package cn.zhangxd.trip.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring-boot 启动入口
 * Created by zhangxd on 16/3/7.
 */
@SpringBootApplication
@EnableAdminServer
public class MonitorApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MonitorApplication.class, args);
    }
}
