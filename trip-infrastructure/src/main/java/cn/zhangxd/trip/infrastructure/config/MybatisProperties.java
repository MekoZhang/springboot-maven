package cn.zhangxd.trip.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Mybatis 配置属性
 * Created by zhangxd on 16/4/18.
 */
@ConfigurationProperties(prefix = "mybatis", ignoreUnknownFields = false)
public class MybatisProperties {

    private String configLocationXml;

    private String mapperLocations;

    private String typeAliasesPackage;

    public String getConfigLocationXml() {
        return configLocationXml;
    }

    public void setConfigLocationXml(String configLocationXml) {
        this.configLocationXml = configLocationXml;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public String getTypeAliasesPackage() {
        return typeAliasesPackage;
    }

    public void setTypeAliasesPackage(String typeAliasesPackage) {
        this.typeAliasesPackage = typeAliasesPackage;
    }

}
