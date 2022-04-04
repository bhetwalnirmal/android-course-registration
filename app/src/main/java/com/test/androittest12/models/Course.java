package com.test.androittest12.models;

public class Course {
    private String name;
    private double fee;
    private int hours;

    public Course (String name, double fee, int hours) {
        this.name = name;
        this.hours = hours;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
