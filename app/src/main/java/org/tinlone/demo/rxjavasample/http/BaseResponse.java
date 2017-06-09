package org.tinlone.demo.rxjavasample.http;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    /**
     * state : 0
     * msg : success
     * data : {}
     */
    @SerializedName("state")
    private int state;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private T data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
