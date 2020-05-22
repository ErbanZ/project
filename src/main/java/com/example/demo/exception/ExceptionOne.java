package com.example.demo.exception;

public class ExceptionOne {
    private int value;
    private String desc;

    public ExceptionOne(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ExceptionOne{" +
                "value=" + value +
                ", desc='" + desc + '\'' +
                '}';
    }
}
