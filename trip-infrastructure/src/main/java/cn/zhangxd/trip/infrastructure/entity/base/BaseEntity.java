package cn.zhangxd.trip.infrastructure.entity.base;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity 基类
 * Created by zhangxd on 16/4/16.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator = "UUID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
