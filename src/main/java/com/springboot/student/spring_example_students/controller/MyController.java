package com.springboot.student.spring_example_students.controller;

import com.springboot.student.spring_example_students.entity.Students;
import com.springboot.student.spring_example_students.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class MyController {

    @Autowired
    private StudentsService studentsService;


    @GetMapping
    public String showAllStudents(Model model) {

        List<Students> allStudents = studentsService.getAllStudents();
        model.addAttribute("students", allStudents);

        return "all-students";
    }

    @GetMapping("/students/{id}")
    public String getStudents(@PathVariable int id, Model model) {
        Students student = studentsService.getStudent(id);

        model.addAttribute("student", student);
        return "get-student";

    }


    @GetMapping("/add-students")
    public String addNewStudents(Model model) {
        Students student = new Students();
        model.addAttribute("students", student);

        return "add-student";
    }

    @PostMapping("/students")
    public String saveStudents(@Valid Students students, Errors errors) {

        if (errors.hasErrors()) {
            System.out.println("---------------------------");
            System.out.println("-------------Ошибка--------------");
            System.out.println("---------------------------");
            return "add-student";
        }

        studentsService.saveStudents(students);
        return "all-students";

    }
    @RequestMapping("/update/{id}")
    public String showUpdateStudent(@PathVariable int id, Model model) {

        Students students = studentsService.getStudent(id);

        model.addAttribute("students", students);

        return "update-student";
    }

 
    @PostMapping("/update/{id}")
    public String updateStudents(@PathVariable int id, Students students, Model model) {

        studentsService.saveStudents(students);
        return "redirect:/";
    }


    @RequestMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id, Model model) {

        studentsService.deleteStudent(id);
        return "redirect:/";

    }


    @GetMapping("/{id}/bucket")
    public String addBucker(@PathVariable long id, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        studentsService.addToUSerBucket(id, principal.getName());
        return "redirect:/";
    }





}
