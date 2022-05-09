package com.springboot.student.spring_example_students.service;

import com.springboot.student.spring_example_students.entity.Role;
import com.springboot.student.spring_example_students.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User saveUser(User user);
    public Role saveRole(Role role);
    public void addRoleToUser(String username, String roleName);

    public User getUser(String username);
    public List<User> getUsers();

}
