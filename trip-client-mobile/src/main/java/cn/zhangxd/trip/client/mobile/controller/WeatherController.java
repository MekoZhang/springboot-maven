package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.client.mobile.common.controller.BaseController;
import cn.zhangxd.trip.client.mobile.constant.Message;
import cn.zhangxd.trip.client.mobile.constant.ReturnCode;
import cn.zhangxd.trip.service.api.entity.Weather;
import cn.zhangxd.trip.service.api.service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/{version}/weather")
public class WeatherController extends BaseController {

    @Autowired
    private IWeatherService weatherService;

    @RequestMapping(value = "/{city}", method = RequestMethod.GET)
    public Map<String, Object> getWeather(
            @PathVariable("version") String version,
            @PathVariable("city") String city
    ) throws Exception {
        Weather weather = weatherService.getWeather(city);
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
        message.put(Message.RETURN_FIELD_DATA, weather);
        return message;
    }

//    @ExceptionHandler(UnexpectedTypeException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, Object> handleUnexpectedTypeException(UnexpectedTypeException ex) {
//        return makeErrorMessage(ReturnCode.INVALID_FIELD, "Type Error", ex.getMessage());
//    }

}
