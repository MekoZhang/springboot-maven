package cn.zhangxd.trip.infrastructure.entity.base;

import java.io.Serializable;

/**
 * Entity 基类
 * Created by zhangxd on 16/4/16.
 */
public class BaseEntity implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
