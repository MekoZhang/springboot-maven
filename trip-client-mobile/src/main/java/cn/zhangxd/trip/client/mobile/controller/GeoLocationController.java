package cn.zhangxd.trip.client.mobile.controller;

import cn.zhangxd.trip.client.mobile.common.controller.BaseController;
import cn.zhangxd.trip.client.mobile.constant.Message;
import cn.zhangxd.trip.client.mobile.constant.ReturnCode;
import cn.zhangxd.trip.service.api.entity.Location;
import cn.zhangxd.trip.service.api.exception.ReverseGeoException;
import cn.zhangxd.trip.service.api.service.IGeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/{version}/geocode")
public class GeoLocationController extends BaseController {

    @Autowired
    private IGeoLocationService geoLocationService;

    @RequestMapping(value = "/{longitude},{latitude}", method = RequestMethod.GET)
    public Map<String, Object> getWeather(
            @PathVariable("version") String version,
            @PathVariable("longitude") double longitude,
            @PathVariable("latitude") double latitude
    ) throws Exception {
        Location location = geoLocationService.getLocation(longitude, latitude);
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
        message.put(Message.RETURN_FIELD_DATA, location);
        return message;
    }

    @ExceptionHandler(ReverseGeoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleReverseGeoException(ReverseGeoException ex) {
        return makeErrorMessage(ReturnCode.CANNOT_REVERSE_GEO, "Cannot Reverse Geolocation", ex.getMessage());
    }

}
