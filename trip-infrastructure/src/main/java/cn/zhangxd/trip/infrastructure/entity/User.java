package cn.zhangxd.trip.infrastructure.entity;

import cn.zhangxd.trip.infrastructure.entity.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "boot_user")
public class User extends BaseEntity {

    private String username;
    private String password;

    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
