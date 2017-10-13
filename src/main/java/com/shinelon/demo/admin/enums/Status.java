package com.shinelon.demo.admin.enums;

public enum Status {
    AVAILABLE(1, "有效"), UNAVAILABLE(0, "无效");
    private Integer value;
    private String msg;

    Status(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getValue() {
        return value;
    }
}
