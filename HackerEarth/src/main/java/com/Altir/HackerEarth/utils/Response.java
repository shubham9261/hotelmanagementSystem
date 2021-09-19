package com.Altir.HackerEarth.utils;

public class Response {
    private Object data;
    private String message;
    private Boolean isOk;
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Boolean getIsOk() {
        return isOk;
    }
    public void setIsOk(Boolean isOk) {
        this.isOk = isOk;
    }

}

