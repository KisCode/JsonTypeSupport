package com.kiscode.gson.support.model;

/**
 * Description: 返回
 * Author: KrisKeno
 * Date : 2020/8/7 9:53 PM
 **/
public class ReturnModel<T> {

    /**
     * success : true
     * code : 0
     * data : {"name":"keno","age":29,"email":"keno@gmail.com"}
     */
    private boolean success;
    private int code;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReturnModel{" +
                "success=" + success +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
