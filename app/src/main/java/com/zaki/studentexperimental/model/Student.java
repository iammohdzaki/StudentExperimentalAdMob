package com.zaki.studentexperimental.model;

public class Student {

    private String mName;
    private int mAge;

    public Student(String mName, int mAge) {
        this.mName = mName;
        this.mAge = mAge;
    }

    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }

}
