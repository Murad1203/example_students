package com.springboot.student.spring_example_students.dto;

import com.springboot.student.spring_example_students.entity.Students;

public class BucketDetailsDTO {

    private long studentId;
    private String name;
    private String surname;
    private int studClass;
    private String club;

    public BucketDetailsDTO(Students students) {
        this.studentId = students.getId();
        this.name = students.getName();
        this.surname = students.getSurname();
        this.studClass = students.getStudClass();
        this.club = students.getClub();
    }


    public BucketDetailsDTO() {
    }


    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getStudClass() {
        return studClass;
    }

    public void setStudClass(int studClass) {
        this.studClass = studClass;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "BucketDetailsDTO{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", studClass=" + studClass +
                ", club='" + club + '\'' +
                '}';
    }
}
