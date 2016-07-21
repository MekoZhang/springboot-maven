package cn.zhangxd.trip.service.api.service;

import cn.zhangxd.trip.service.api.entity.Location;
import cn.zhangxd.trip.service.api.exception.ReverseGeoException;

/**
 * 位置解析接口
 * Created by zhangxd on 16/6/30.
 */
public interface IGeoLocationService {

    Location getLocation(double longitude, double latitude) throws ReverseGeoException;

}
