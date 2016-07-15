package cn.zhangxd.trip.web.admin.base;

import cn.zhangxd.trip.util.upload.FileOperatorDiskImpl;
import cn.zhangxd.trip.web.admin.utils.upload.FileManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传配置
 * Created by zhangxd on 16/7/14.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("upload")
public class UploadConfig {

    private String workFolderName;
    private String accessUrl;

    @Bean
    public FileManager fileManager() {
        FileManager fileManager = new FileManager();
        FileOperatorDiskImpl fileOperator = new FileOperatorDiskImpl();
        fileOperator.setWorkFolderName(workFolderName);
        fileOperator.setAccessUrl(accessUrl);
        fileManager.setFileOperator(fileOperator);
        return fileManager;
    }

    public String getWorkFolderName() {
        return workFolderName;
    }

    public void setWorkFolderName(String workFolderName) {
        this.workFolderName = workFolderName;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }
}
