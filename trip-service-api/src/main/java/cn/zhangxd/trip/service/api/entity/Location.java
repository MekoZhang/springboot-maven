package cn.zhangxd.trip.service.api.entity;

import java.io.Serializable;

/**
 * 地理位置
 * Created by zhangxd on 16/6/30.
 */
public class Location implements Serializable {
    /** 国家 */
    private String country;
    /** 城市 */
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
