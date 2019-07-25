package com.namimono.securitylogin.demo.config.result;

import com.alibaba.fastjson.JSON;

public class MyResult {

    private Integer code;
    private String msg;
    private Object data;

    public MyResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public MyResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static MyResult getSuccess(){

        return new MyResult(200,"操作成功");
    }
    public static MyResult getFail(){

        return new MyResult(400,"操作失败");
    }
    public static MyResult getUnAuthenticate(){

        return new MyResult(400,"无权限");
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
