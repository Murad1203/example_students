package com.springboot.student.spring_example_students.dao;

import com.springboot.student.spring_example_students.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
