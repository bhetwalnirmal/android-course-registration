package com.test.androittest12.models;

// type = 1 for Graduated
// type = 2 for Undergraduated

public class CourseChoices {
    private String name;
    private int type;

    public CourseChoices(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
