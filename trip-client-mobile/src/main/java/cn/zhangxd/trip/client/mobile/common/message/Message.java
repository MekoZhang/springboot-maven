package cn.zhangxd.trip.client.mobile.common.message;

/**
 * 接口传输的数据格式
 * Created by zhangxd on 16/3/13.
 */
public class Message {

    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Message() {

    }

    public Message(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Message(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setMsg(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = "no data";
    }

    public void setMsg(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
