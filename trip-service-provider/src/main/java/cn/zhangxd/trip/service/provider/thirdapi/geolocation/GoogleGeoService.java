package cn.zhangxd.trip.service.provider.thirdapi.geolocation;

import cn.zhangxd.trip.service.api.entity.Location;
import cn.zhangxd.trip.service.api.exception.ReverseGeoException;
import cn.zhangxd.trip.util.StringHelper;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * 短信工具类
 * Created by zhangxd on 16/6/29.
 */
@Service
@EnableConfigurationProperties
@ConfigurationProperties("api.google.map")
public class GoogleGeoService {

    private static Logger logger = LoggerFactory.getLogger(GoogleGeoService.class);

    private String key;


    public Location getLocation(double longitude, double latitude) throws ReverseGeoException {

        GeoApiContext context = new GeoApiContext().setApiKey(key);
        GeocodingResult[] results;
        try {
            results = GeocodingApi
                    .reverseGeocode(context, new LatLng(latitude, longitude))
                    .language("zh-CN")
                    .resultType(AddressType.COUNTRY, AddressType.LOCALITY)
                    .await();
        } catch (Exception e) {
            logger.error(String.format("坐标 '%s,%s' 解析失败", longitude, latitude));
            throw new ReverseGeoException(String.format("坐标 '%s,%s' 解析失败", longitude, latitude));
        }

        String country = "";
        String city = "";
        if (results != null && results.length > 0) {
            for (GeocodingResult result : results) {
                AddressComponent[] addressComponents = result.addressComponents;
                if (addressComponents != null && addressComponents.length > 0) {
                    if (ArrayUtils.contains(result.types, AddressType.COUNTRY)) {
                        for (AddressComponent addressComponent : addressComponents) {
                            AddressComponentType[] addressComponentTypes = addressComponent.types;
                            if (ArrayUtils.contains(addressComponentTypes, AddressComponentType.COUNTRY)) {
                                country = addressComponent.longName;
                            }
                        }
                        continue;
                    }
                    for (AddressComponent addressComponent : addressComponents) {
                        AddressComponentType[] addressComponentTypes = addressComponent.types;
                        if (ArrayUtils.contains(addressComponentTypes, AddressComponentType.LOCALITY)) {
                            city = addressComponent.longName;
                        }
                    }
                }
            }
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
