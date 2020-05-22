package com.example.demo.entity;

public class Baggage {
    private double weight;
    private double length;
    private double width;
    private double height;
    private double size;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Baggage(double weight, double length, double width, double height) {
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.size=height+length+width;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getSize() {
        return size;
    }

    @Override
    public String toString() {
        return  length + "-" + width + "-" + height+ "-" +weight  ;
    }
}
