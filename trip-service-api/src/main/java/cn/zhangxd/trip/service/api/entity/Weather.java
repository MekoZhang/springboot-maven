package cn.zhangxd.trip.service.api.entity;

import java.io.Serializable;

/**
 * 天气
 * Created by zhangxd on 16/6/30.
 */
public class Weather implements Serializable {

    /** 当前温度 */
    private String tmp;
    /** 最高温 */
    private String max;
    /** 最低温 */
    private String min;
    /** 天气描述 */
    private String txt;

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
