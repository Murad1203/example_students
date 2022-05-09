package com.springboot.student.spring_example_students;

import com.springboot.student.spring_example_students.entity.Bucket;
import com.springboot.student.spring_example_students.entity.Role;
import com.springboot.student.spring_example_students.entity.Students;
import com.springboot.student.spring_example_students.entity.User;
import com.springboot.student.spring_example_students.service.StudentsService;
import com.springboot.student.spring_example_students.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class SpringExampleStudentsApplication {

	public static void main(java.lang.String[] args) {
		SpringApplication.run(SpringExampleStudentsApplication.class, args);
	}



	@Bean
	CommandLineRunner run(StudentsService studentsService) {
		return args -> {
			studentsService.saveStudents(new Students("Murad", "Mirzoev", 7, "MIT"));
		};
	}
//	@Bean
//	CommandLineRunner runs(UserService userService) {
//		return args -> {
//
//
//			System.out.println("CommandLineRunner Работает");
//			//studentsService.saveStudents(new Students("Micga", "Tregulov", 7, "Club"));
//
//			userService.saveRole(new Role("ROLE_ADMIN"));
//			userService.saveRole(new Role("ROLE_STUDENT"));
//			userService.saveRole(new Role("ROLE_TEACHER"));
//
//
//			userService.saveUser(new User("joe", "emas@gmail.com", "123", new HashSet<>(), null));
////			userService.saveUser(new User("Mir", "miras@gmail.com", "$2a$12$ypevAZiuVJ3R/jdE2VLHwOlqhJe02J5zi.6UpvyUB7p/cTZ1z5Td.", new ArrayList<>()));
////			userService.saveUser(new User("Doje", "dons@gmail.com", "{bcrypt}$2a$12$ypevAZiuVJ3R/jdE2VLHwOlqhJe02J5zi.6UpvyUB7p/cTZ1z5Td.", new ArrayList<>()));
////			userService.saveUser(new User("Dimas", "dimas@gmail.com", "$2a$12$ypevAZiuVJ3R/jdE2VLHwOlqhJe02J5zi.6UpvyUB7p/cTZ1z5Td.", new ArrayList<>()));
//
////			$2a$12$.YeoTW1.Lw5RPeOZFQXFjeh55sCKQsC7X1kna.VDyNLvWZxIhX71u
//			userService.addRoleToUser("joe", "ROLE_ADMIN");
////			userService.addRoleToUser("Mir", "ROLE_STUDENT");
////			userService.addRoleToUser("Doje", "ROLE_TEACHER");
////			userService.addRoleToUser("Dimas", "ROLE_ADMIN");
//
//
//		};
//	}

}


