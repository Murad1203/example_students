package com.springboot.student.spring_example_students.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Valid
    @NotBlank(message = "Поле не должно быть пустым")
    private String username;


    @Valid
    @NotBlank
    @Email
    private String email;


    @Valid
    @NotNull
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_s_roles",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "roles_id"))
//    private List<Role> roles = new ArrayList<>();




    @ManyToMany
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;


    public User() {
    }

    public User(@Valid @NotBlank(message = "Поле не должно быть пустым") String username, @Valid @NotBlank @Email String email, @Valid @NotNull String password, Set<Role> roles, Bucket bucket) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.bucket = bucket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", bucket=" + bucket +
                '}';
    }
}
