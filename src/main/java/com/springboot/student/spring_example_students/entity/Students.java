package com.springboot.student.spring_example_students.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @Valid
    @Column(name = "name")
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;


    @Valid
    @Column(name = "surname")
    @NotBlank(message = "Поле не должно быть пустым")
    private String surname;



    @Valid
    @Column(name = "class")
    @NotNull(message = "Поле не должно быть пустым")
    @Max(11)
    private int studClass;




    @Column(name = "club")
    private String club;


    public Students() {
    }

    public Students(String name, String surname, int studClass, String club) {
        this.name = name;
        this.surname = surname;
        this.studClass = studClass;
        this.club = club;

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", studClass=" + studClass +
                ", club='" + club + '\'' +
                '}';
    }
}
