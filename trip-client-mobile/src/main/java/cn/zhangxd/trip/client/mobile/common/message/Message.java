package cn.zhangxd.trip.client.mobile.common.message;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * 接口传输的数据格式
 * Created by zhangxd on 16/3/13.
 */
public class Message {

    private int code;
    private String msg;
    private Object data;
    private Date now;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public Date getNow() {
        return new Date();
    }

    public Message() {
        this.code = HttpStatus.OK.value();
        this.msg = "";
    }

    public Message(int code, String message) {
        this.code = code;
        this.msg = message;
        this.data = "";
    }

    public Message(int code, String message, Object data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public void setMsg(int code, String message) {
        this.code = code;
        this.msg = message;
        this.data = "";
    }

    public void setMsg(int code, String message, Object data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public void setMsg(Object data) {
        this.code = HttpStatus.OK.value();
        this.msg = "";
        this.data = data;
    }

}
