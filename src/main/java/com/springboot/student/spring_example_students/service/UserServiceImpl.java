package com.springboot.student.spring_example_students.service;


import com.springboot.student.spring_example_students.dao.RoleRepository;
import com.springboot.student.spring_example_students.dao.StudentsRepository;
import com.springboot.student.spring_example_students.dao.UserRepository;
import com.springboot.student.spring_example_students.entity.Role;
import com.springboot.student.spring_example_students.entity.Students;
import com.springboot.student.spring_example_students.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {




    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StudentsRepository studentsRepository;



    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);


        if (user == null) {
            throw new UsernameNotFoundException("Unknown user:" + username);
        }
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        });

        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);


//        UserDetails user = org.springframework.security.core.userdetails.User.builder()
//                .username(myUser.getUsername())
//                .password(myUser.getPassword())
//                .roles(myUser.getRoles().toString())
//                .build();
//
//
//        System.out.println("------------" + myUser.getRoles().toString());
//
//        return user;



    }



    private String encodeString(String password) {
        return passwordEncoder.encode(password);
    }


    @Override
    public User saveUser(User user) {

        Set<Role> col = user.getRoles();

//        System.out.println("-----------------");
//
//        col.forEach(x -> {
//            System.out.println(x);
//        });
//
//        System.out.println("-----------------");

//        user.setRoles(Collections.singleton(user.));
        user.setRoles(user.getRoles());

        user.setPassword(encodeString(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);

    }


    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }



}
