package com.springboot.student.spring_example_students.dao;

import com.springboot.student.spring_example_students.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Integer> {
    Students findByName(String studName);

}
