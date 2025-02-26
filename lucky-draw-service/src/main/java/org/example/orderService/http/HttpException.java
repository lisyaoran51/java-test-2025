package org.example.orderService.http;

public enum HttpException {
    SUCCESS(1, "成功"),
    SYSTEM_ERROR(10000, "系統異常");

    private Integer code;
    private String msg;

    private HttpException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
