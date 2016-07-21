package cn.zhangxd.trip.service.api.service;

import cn.zhangxd.trip.service.api.entity.Weather;
import cn.zhangxd.trip.service.api.exception.GetWeatherException;

/**
 * 天气接口
 * Created by zhangxd on 16/6/30.
 */
public interface IWeatherService {

    Weather getWeather(String city) throws GetWeatherException;

}
