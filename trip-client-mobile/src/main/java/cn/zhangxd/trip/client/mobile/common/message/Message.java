package cn.zhangxd.trip.client.mobile.common.message;

/**
 * 接口传输的数据格式
 * Created by zhangxd on 16/3/13.
 */
public class Message {

    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public Message() {
        this.msg = "";
        this.data = "";
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
    }

    public void setMsg(int code, String message, Object data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public void setMsg(Object data) {
        this.data = data;
    }

}
