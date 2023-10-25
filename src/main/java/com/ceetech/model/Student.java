package com.ceetech.model;

import java.util.Objects;

public class Student {
    private  int id;
    private String regNo;
    private  String studentName;

    public Student() {
    }

    public Student(int id, String regNo, String studentName) {
        this.id = id;
        this.regNo = regNo;
        this.studentName = studentName;
    }

    public Student(String regNo, String studentName) {
        this.regNo = regNo;
        this.studentName = studentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", regNo='" + regNo + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(regNo, student.regNo) && Objects.equals(studentName, student.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regNo, studentName);
    }
}
