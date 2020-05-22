package com.example.demo.exception;

public class ExceptionTwo {
    private int value;
    private String desc;
    private int num_excess;
    private double weight_excess;//超出量
    private double sieze_excess;//超出量

    public ExceptionTwo(int value, String desc, int num_excess, double weight_excess, double sieze_excess) {
        this.value = value;
        this.desc = desc;
        this.num_excess = num_excess;
        this.weight_excess = weight_excess;
        this.sieze_excess = sieze_excess;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public int getNum_excess() {
        return num_excess;
    }

    public double getWeight_excess() {
        return weight_excess;
    }

    public double getSieze_excess() {
        return sieze_excess;
    }
}
