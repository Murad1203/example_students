package com.springboot.student.spring_example_students.controller;

import com.springboot.student.spring_example_students.dto.BucketDTO;
import com.springboot.student.spring_example_students.dto.UserDTO;
import com.springboot.student.spring_example_students.entity.Bucket;
import com.springboot.student.spring_example_students.entity.Students;
import com.springboot.student.spring_example_students.entity.User;
import com.springboot.student.spring_example_students.service.BucketService;
import com.springboot.student.spring_example_students.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class BucketController {

    @Autowired
    private BucketService bucketService;


    @Autowired
    private UserService userService;

    @GetMapping("/bucket")
    public String aboutBucket(Model model, Principal principal) {

        if(principal == null) {
            model.addAttribute("bucket", new BucketDTO());
        }
        else {
            BucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }

        return "bucket";


    }


    @GetMapping("/bucket/{id}")
    public String deleteItem(@PathVariable long id, Principal principal) {



        bucketService.deleteItem(principal.getName(), id);

        return "redirect:/bucket";

    }


}
