package com.springboot.student.spring_example_students.service;

import com.springboot.student.spring_example_students.dao.BucketRepo;
import com.springboot.student.spring_example_students.dao.StudentsRepository;
import com.springboot.student.spring_example_students.dao.UserRepository;
import com.springboot.student.spring_example_students.entity.Bucket;
import com.springboot.student.spring_example_students.entity.Students;
import com.springboot.student.spring_example_students.entity.User;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BucketService bucketService;



    @Override
    public List<Students> getAllStudents() {

        return studentsRepository.findAll();
    }

    //Вывод единственного пользователя
    @Override
    public Students getStudent(int id) {


        Students students = null;

        Optional<Students> std = studentsRepository.findById(id);

        if(std.isPresent()) {
            students = std.get();
        }
        return students;
    }

    @Override
    public void saveStudents(Students students) {
        studentsRepository.save(students);
    }

    @Override
    @Transactional
    public void addToUSerBucket(Long studentId, String username) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found - " + username);
        }

        Bucket bucket = user.getBucket();

        if (bucket == null) {
            Bucket newbucket = bucketService.createBucket(user, Collections.singletonList(studentId));
            user.setBucket(newbucket);
            userRepository.save(user);
        } else {
            bucketService.addStudents(bucket, Collections.singletonList(studentId));
        }

    }

    @Override
    public void deleteStudent(int id) {
        studentsRepository.deleteById(id);
    }

}
