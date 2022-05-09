package com.springboot.student.spring_example_students.controller;

import com.springboot.student.spring_example_students.dao.RoleRepository;
import com.springboot.student.spring_example_students.dao.UserRepository;
import com.springboot.student.spring_example_students.entity.Role;
import com.springboot.student.spring_example_students.entity.User;
import com.springboot.student.spring_example_students.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
//@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public List<User> getUsers() {

        List<User> allUsers = userService.getUsers();
        return allUsers;
    }



    @GetMapping("/login")
    public String loginUser() {
        return "login";
    }


    @RequestMapping("/registration")
    public String addUser(@ModelAttribute("users") User user, Model model) {

//        User user = new User();
        //Role role = new Role();

        List<Role> roles = roleRepository.findAll();


//        model.addAttribute("users", user);
        model.addAttribute("roles", roles);

        return "add-user";
    }


    @PostMapping("/user/save")
    public String saveUser(@Validated User user , Errors errors) {

        if(errors.hasErrors()) {

            System.out.println("--------------Errors---------------");
            return "add-user";

        }
        userService.saveUser(user);
        return "redirect:/login";

    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {

        URI uri  = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }


//    @PostMapping("/role/addtouser")
//    public String addRoleToUser(@RequestBody RoleToUserForm form) {
//
//        userService.addRoleToUser(form.getUsername(), form.getRolename());
//        return "redirect:/loging";
//
//    }
//
//
//
//
}
//
//@Data
//class RoleToUserForm {
//
//    private String username;
//    private String rolename;
//
//}