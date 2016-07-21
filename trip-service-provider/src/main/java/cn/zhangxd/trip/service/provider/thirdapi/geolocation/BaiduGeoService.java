package cn.zhangxd.trip.service.provider.thirdapi.geolocation;

import cn.zhangxd.trip.service.api.entity.Location;
import cn.zhangxd.trip.service.api.exception.ReverseGeoException;
import cn.zhangxd.trip.service.provider.thirdapi.geolocation.BaiduGeoBean.BaiduGeoResult;
import cn.zhangxd.trip.service.provider.thirdapi.geolocation.BaiduGeoBean.BaiduGeoResult.AddressComponent;
import cn.zhangxd.trip.util.HttpClientUtils;
import cn.zhangxd.trip.util.StringHelper;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信工具类
 * Created by zhangxd on 16/6/29.
 */
@Service
@EnableConfigurationProperties
@ConfigurationProperties("api.baidu.map")
public class BaiduGeoService {

    private static Logger logger = LoggerFactory.getLogger(BaiduGeoService.class);

    private String key;

    public Location getLocation(double longitude, double latitude) throws ReverseGeoException {

        String url = "http://api.map.baidu.com/geocoder/v2/";

        HttpClient client = HttpClientUtils.getConnection();

        Map<String, String> params = new HashMap<>();
        params.put("ak", key);
        params.put("output", "json");
        params.put("coordtype", "wgs84ll");
        params.put("location", String.format("%s,%s", latitude, longitude));

        String country = "";
        String city = "";

        HttpUriRequest get = HttpClientUtils.getRequestMethod(params, url, HttpClientUtils.METHOD_GET);
        HttpResponse response;
        try {
            response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                String message = EntityUtils.toString(entity, "utf-8");

                BaiduGeoBean baiduGeoBean = new Gson().fromJson(message, BaiduGeoBean.class);
                if (baiduGeoBean.getStatus() == 0) {
                    BaiduGeoResult result = baiduGeoBean.getResult();
                    if (result != null) {
                        AddressComponent addressComponent = result.getAddressComponent();
                        if (addressComponent != null) {
                            country = addressComponent.getCountry();
                            city = addressComponent.getCity();
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error(String.format("坐标 '%s,%s' 解析失败", longitude, latitude));
            throw new ReverseGeoException(String.format("坐标 '%s,%s' 解析失败", longitude, latitude));
        }

        if (StringHelper.isEmpty(country)) {
            logger.error(String.format("坐标 '%s,%s' 解析失败", longitude, latitude));
            throw new ReverseGeoException(String.format("坐标 '%s,%s' 解析失败", longitude, latitude));
        }
        Location location = new Location();
        location.setCountry(country);
        location.setCity(city);

        logger.info(String.format("lng: %s, lat: %s :: country: %s, city: %s", longitude, latitude, country, city));
        return location;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
