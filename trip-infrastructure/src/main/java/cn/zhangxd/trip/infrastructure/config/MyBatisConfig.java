package cn.zhangxd.trip.infrastructure.config;

import cn.zhangxd.trip.service.api.entity.base.BaseEntity;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class MyBatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        Resource configLocation = new ClassPathResource("mybatis-config.xml");
        Resource[] mapperLocationsResources = new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/*.xml");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(configLocation);
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("cn.zhangxd.trip.service.api.entity");
        sqlSessionFactoryBean.setTypeAliasesSuperType(BaseEntity.class);
        sqlSessionFactoryBean.setMapperLocations(mapperLocationsResources);
        return sqlSessionFactoryBean;
    }

}
