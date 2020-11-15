package com.kkhome.excel.constant;

public enum Button {

    A(1, ""),
    B(2, ""),
    C(3, "");

    private int code;
    private String text;

    Button(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
