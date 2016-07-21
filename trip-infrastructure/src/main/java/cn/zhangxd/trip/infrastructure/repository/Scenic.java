package cn.zhangxd.trip.infrastructure.repository;

import java.util.Date;

/**
 * Created by zhangxd on 16/7/16.
 */
public class Scenic {
    /**
     * _id : 5789f095290d4fd2bbb79d65
     * id : 00396aede7d64415bb14efd9374d40ed
     * name : 爱宝乐园
     * city : fe98e1aa3d1211e5a80a9fa661f29b31
     * type : 1
     * description : 爱宝乐园地处京畿道龙仁市，开业已经有50多年。在这里你可以坐上吉普车与野生动物们亲密接触。去以世界各国主要城市为主题打造的环球集市购买可爱伴手礼。参加一年四季变化不停的花之庆典、观看欢欣鼓舞的花车游行。
     * address : 京畿道龙仁市处仁区蒲谷邑前贷里310号
     * longitude : 127.177554
     * latitude : 37.241086
     * score : 4
     * price : 1日通票：成人48000韩元，青少年41000韩元，儿童和老人38000韩元。夜间通票（17:00以后）：成人40000韩元，青少年34000韩元，儿童和老人31000韩元。2日通票：成人77000韩元，青少年65000韩元，儿童和老人61000韩元。1日门票：成人40000韩元，青少年34000韩元，儿童和老人31000韩元。夜间门票（17:00以后）：成人32000韩元，青少年29000韩元，儿童和老人27000韩元。
     * hours : 乐园每天开放时间不同，一般为10:00-22:00，夏季营业时间有所延长。
     * tips :
     * cooper : 0
     * level : 0
     * published : 1
     * create_by : 1
     * create_date : 2015-12-03T16:59:31.000Z
     * update_by : 1
     * update_date : 2015-08-12T16:59:30.000Z
     * remarks : null
     * del_flag : 0
     */

    private String id;
    private String name;
    private String city;
    private String type;
    private String description;
    private String address;
    private double longitude;
    private double latitude;
    private int score;
    private String price;
    private String hours;
    private String tips;
    private String cooper;
    private int level;
    private String published;
    private String create_by;
    private Date create_date;
    private String update_by;
    private Date update_date;
    private String remarks;
    private String del_flag;
    private double[] location;

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getCooper() {
        return cooper;
    }

    public void setCooper(String cooper) {
        this.cooper = cooper;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }
}
