package com.qunar.campus.spring.tutorial.homework3.bean;

/**
 * Description: APIResponse
 *
 * 前后端接口对象
 *
 * @author yushen.ma
 * @version 2015-04-04 21:23
 */
public class APIResponse<T> {

    private int status;

    private String message;

    private T data;

    public APIResponse() { }

    public APIResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> APIResponse<T> success(T data) {
        return new APIResponse<T>(1, "success", data);
    }

    @SuppressWarnings("unchecked")
    public static APIResponse failed( String errorMsg) {
        return new APIResponse(0, errorMsg, null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
