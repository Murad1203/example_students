package com.springboot.student.spring_example_students.service;

import com.springboot.student.spring_example_students.dto.BucketDTO;
import com.springboot.student.spring_example_students.entity.Bucket;
import com.springboot.student.spring_example_students.entity.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> studentsIds);
    void addStudents(Bucket bucket, List<Long> studentsId);

    BucketDTO getBucketByUser(String name);


    void deleteItem(String name, long id);
}
