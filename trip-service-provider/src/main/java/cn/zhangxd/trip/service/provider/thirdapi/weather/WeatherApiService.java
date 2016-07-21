package cn.zhangxd.trip.service.provider.thirdapi.weather;

import cn.zhangxd.trip.infrastructure.repo.RedisRepo;
import cn.zhangxd.trip.service.api.exception.GetWeatherException;
import cn.zhangxd.trip.util.HttpClientUtils;
import cn.zhangxd.trip.service.provider.thirdapi.weather.WeatherBean.HeWeatherDataServiceBean;
import cn.zhangxd.trip.util.StringHelper;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 天气服务
 * Created by zhangxd on 16/6/30.
 */
@Service
@EnableConfigurationProperties
@ConfigurationProperties("api.hefeng.weather")
public class WeatherApiService {

    private static Logger logger = LoggerFactory.getLogger(WeatherApiService.class);

    @Autowired
    private RedisRepo redisRepo;

    private static final String REDIS_PREFIX = "weather_";

    private String key;
    private String url;

    public HeWeatherDataServiceBean get(String city) throws GetWeatherException {

        try {
            String weatherCache = redisRepo.get(REDIS_PREFIX + city);
            if (StringHelper.isNotEmpty(weatherCache)) {
                return new Gson().fromJson(weatherCache, HeWeatherDataServiceBean.class);
            }
        } catch (Exception e) {
            logger.error("Redis 异常", e);
        }

        HttpClient client = HttpClientUtils.getConnection();

        Map<String, String> params = new HashMap<>();
        params.put("city", city);
        params.put("key", key);

        HttpUriRequest get = HttpClientUtils.getRequestMethod(params, url, HttpClientUtils.METHOD_GET);
        HttpResponse response;
        try {
            response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                String message = EntityUtils.toString(entity, "utf-8");

                WeatherBean weatherBean = new Gson().fromJson(message, WeatherBean.class);

                HeWeatherDataServiceBean bean = weatherBean.getService().get(0);
                String status = bean.getStatus();
                if ("ok".equals(status)) {
                    try {
                        redisRepo.setExpire(REDIS_PREFIX + city, new Gson().toJson(bean), 7200); //缓存2H
                    } catch (Exception e) {
                        logger.error("Redis 异常", e);
                    }
                    return bean;
                } else {
                    logger.error(String.format("城市 '%s' 天气获取失败", city));
                    throw new GetWeatherException(String.format("城市 '%s' 天气获取失败", city));
                }
            } else {
                logger.error(String.format("城市 '%s' 天气获取失败", city));
                throw new GetWeatherException(String.format("城市 '%s' 天气获取失败", city));
            }
        } catch (IOException e) {
            logger.error(String.format("城市 '%s' 天气获取失败", city));
            throw new GetWeatherException(String.format("城市 '%s' 天气获取失败", city));
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
