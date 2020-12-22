package cn.offcn.utils;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Crm自定义响应结构
 */
public class OAResult implements Serializable{

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static OAResult build(Integer status, String msg, Object data) {
        return new OAResult(status, msg, data);
    }

    public static OAResult ok(Object data) {
        return new OAResult(data);
    }

    public static OAResult ok(Integer status,String message) {
        return new OAResult(status,message);
    }

    public static OAResult ok() {
        return new OAResult(null);
    }

    public OAResult() {

    }

    public static OAResult build(Integer status, String msg) {
        return new OAResult(status, msg, null);
    }

    public OAResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public OAResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public OAResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
