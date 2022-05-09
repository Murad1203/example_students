package com.springboot.student.spring_example_students.dao;

import com.springboot.student.spring_example_students.entity.Bucket;
import com.springboot.student.spring_example_students.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BucketRepo extends JpaRepository<Bucket, Long> {

//
//    @Query(" ")
//    @Modifying
//    public void deleteByUserAndStudents(long buckId, long studId);

}
