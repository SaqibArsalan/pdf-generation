package com.example.pfdgeneration.model;

import java.time.LocalDate;

public class Student {
    private Integer id;
    private String name;
    private String lastName;
    private LocalDate birthday;
    private String nationality;
    private String university;
    private Boolean active;

    public Student() {
    }

    public Student(Integer id, String name, String lastName, LocalDate birthday, String nationality, String university, Boolean active) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.nationality = nationality;
        this.university = university;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUniversity() {
        return university;
    }

    public Boolean getActive() {
        return active;
    }
}