package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.service.api.entity.Weather;
import cn.zhangxd.trip.service.api.service.IWeatherService;
import cn.zhangxd.trip.service.provider.common.service.BaseService;
import cn.zhangxd.trip.service.provider.thirdapi.weather.WeatherApiService;
import cn.zhangxd.trip.service.provider.thirdapi.weather.WeatherBean.HeWeatherDataServiceBean;
import cn.zhangxd.trip.service.provider.thirdapi.weather.WeatherBean.HeWeatherDataServiceBean.NowBean;
import cn.zhangxd.trip.service.provider.thirdapi.weather.WeatherBean.HeWeatherDataServiceBean.DailyForecastBean.TmpBean;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(protocol = {"dubbo"}, timeout = 5000)
@Transactional(readOnly = true)
public class WeatherService extends BaseService implements IWeatherService {

    @Autowired
    WeatherApiService weatherApiService;

    @Override
    public Weather getWeather(String city) {
        HeWeatherDataServiceBean bean = weatherApiService.get(city);
        if (bean != null) {
            NowBean nowBean = bean.getNow();
            String tmp = nowBean.getTmp();
            String txt = nowBean.getCond().getTxt();

            TmpBean tmpBean = bean.getDailyForecast().get(0).getTmp();
            String max = tmpBean.getMax();
            String min = tmpBean.getMin();

            Weather weather = new Weather();
            weather.setTmp(tmp);
            weather.setTxt(txt);
            weather.setMax(max);
            weather.setMin(min);
            return weather;
        }
        return null;
    }
}
