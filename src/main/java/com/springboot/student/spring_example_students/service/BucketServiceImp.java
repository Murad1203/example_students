package com.springboot.student.spring_example_students.service;

import com.springboot.student.spring_example_students.dao.BucketRepo;
import com.springboot.student.spring_example_students.dao.StudentsRepository;
import com.springboot.student.spring_example_students.dto.BucketDTO;
import com.springboot.student.spring_example_students.dto.BucketDetailsDTO;
import com.springboot.student.spring_example_students.entity.Bucket;
import com.springboot.student.spring_example_students.entity.Students;
import com.springboot.student.spring_example_students.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BucketServiceImp implements BucketService {

    @Autowired
    private BucketRepo bucketRepo;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private UserService userService;


    @PersistenceContext
    EntityManager entityManager;

//    @PersistenceUnit
//    EntityManagerFactory entityManagerFactory;


    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> studentsIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Students> studentsList = getCollectRefStudentsByIds(studentsIds);
        bucket.setStudents(studentsList);
        return bucketRepo.save(bucket);
    }

    private List<Students> getCollectRefStudentsByIds(List<Long> studentsIds) {
        return studentsIds.stream()
                .map((Long id) -> studentsRepository.getById(Math.toIntExact(id)))
                .collect(Collectors.toList());
    }

    @Override
    public void addStudents(Bucket bucket, List<Long> studentsId) {
        List<Students> students = bucket.getStudents();
        List<Students> newStudentsList = students == null ? new ArrayList<>() : new ArrayList<>(students);
        newStudentsList.addAll(getCollectRefStudentsByIds(studentsId));
        bucket.setStudents(newStudentsList);
        bucketRepo.save(bucket);

    }

    @Override
    public BucketDTO getBucketByUser(String name) {

        User user = userService.getUser(name);
        if(user == null || user.getBucket() == null) {
            return new BucketDTO();
        }
        BucketDTO bucketDTO = new BucketDTO();

        Map<Long, BucketDetailsDTO> mapByStudentId = new HashMap<>();

        List<Students> studentsList = user.getBucket().getStudents();
        for(Students student: studentsList) {
            BucketDetailsDTO detail = mapByStudentId.get(student.getId());
            if (detail == null) {
                mapByStudentId.put((long) student.getId(), new BucketDetailsDTO(student));

            }
        }

        bucketDTO.setBucketDetails(new ArrayList<>(mapByStudentId.values()));
        bucketDTO.aggregate();

        return bucketDTO;
    }

    @Override
    @Transactional
    public void deleteItem(String name, long id) {

//        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("___________________________");
        System.out.println("Функция работает");

        User user = userService.getUser(name);
        Bucket bucket_original = user.getBucket();

        Bucket bucket = entityManager.find(Bucket.class, bucket_original.getStudId(id));
        entityManager.remove(bucket);

        System.out.println("Успешное удаление");

//        bucket.getStudId(id);
//        entityManager.createQuery("DELETE FROM Bucket b WHERE b.user.id = ?1 AND ");


//        User user = userService.getUser()



    }
}
