package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.service.api.entity.Location;
import cn.zhangxd.trip.service.api.exception.ReverseGeoException;
import cn.zhangxd.trip.service.api.service.IGeoLocationService;
import cn.zhangxd.trip.service.provider.common.service.BaseService;
import cn.zhangxd.trip.service.provider.thirdapi.geolocation.BaiduGeoService;
import cn.zhangxd.trip.service.provider.thirdapi.geolocation.GoogleGeoService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.Transactional;

@Service(protocol = {"dubbo"}, timeout = 5000)
@Transactional(readOnly = true)
@EnableConfigurationProperties
@ConfigurationProperties("api.map")
public class GeoLocationService extends BaseService implements IGeoLocationService {

    @Autowired
    GoogleGeoService googleGeoService;
    @Autowired
    BaiduGeoService baiduGeoService;

    private String enable;

    private static final String ENABLE_BAIDU = "baidu";
    private static final String ENABLE_GOOGLE = "google";

    @Override
    public Location getLocation(double longitude, double latitude) throws ReverseGeoException {

        Location location;

        switch (enable) {
            case ENABLE_BAIDU:
                location = baiduGeoService.getLocation(longitude, latitude);
                break;
            case ENABLE_GOOGLE:
                location = googleGeoService.getLocation(longitude, latitude);
                break;
            default:
                location = baiduGeoService.getLocation(longitude, latitude);
        }

        return location;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
