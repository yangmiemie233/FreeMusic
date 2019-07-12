package com.kerwin.server.utils;

public class requestAPI implements java.io.Serializable{



    private boolean flg;

    private String msg;

    private Object data;

    public requestAPI(Object data) {
        this.data = data;
    }

    public requestAPI(boolean flg) {
        this.flg = flg;
    }

    public requestAPI(boolean flg, String msg) {
        this.flg = flg;
        this.msg = msg;
    }

    public requestAPI(boolean flg, String msg, Object data) {
        this.flg = flg;
        this.msg = msg;
        this.data = data;
    }

    public requestAPI() {
    }

    public boolean isFlg() {
        return flg;
    }

    public void setFlg(boolean flg) {
        this.flg = flg;
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
