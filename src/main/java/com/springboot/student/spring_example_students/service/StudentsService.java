package com.springboot.student.spring_example_students.service;

import com.springboot.student.spring_example_students.entity.Students;

import java.util.List;

public interface StudentsService {

    public List<Students> getAllStudents();
    public Students getStudent(int id);
    public void saveStudents(Students students);

    public void addToUSerBucket(Long studentId, String username);

    public void deleteStudent(int id);
}


