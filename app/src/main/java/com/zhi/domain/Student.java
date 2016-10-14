package com.zhi.domain;

/**
 * Created by Administrator on 2016/10/14.
 */
public class Student {

    private int id;
    private String name;
    private int grade;

    public Student(){

    }

    public Student(String name, int grade){
        this.name = name;
        this.grade = grade;
    }

    public Student(int id, String name, int grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student-----:"+"id:"+id+";name:"+name+";grade:"+grade;
    }
}