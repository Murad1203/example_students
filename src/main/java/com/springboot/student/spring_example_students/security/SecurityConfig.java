package com.springboot.student.spring_example_students.security;


import com.springboot.student.spring_example_students.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    private UserDetailsService userDetailsService;


    private PasswordEncoder passwordEncoder;


//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//
//    }




//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

       auth.ldapAuthentication()
               .userDnPatterns("uid={0}, ou=people")
               .groupSearchBase("ou=groups")
               .contextSource()

               .url("ldap://localhost:8389/dc=springframework,dc=org")

               .and()

               .passwordCompare()
               .passwordEncoder(new BCryptPasswordEncoder())
               .passwordAttribute("userPassword");

        //auth.authenticationProvider(daoAuthenticationProvider());
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable().authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/add-students", "/delete/{id}").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").permitAll()
//                .loginProcessingUrl("/perform-login")
//                .usernameParameter("user")
//                .passwordParameter("pass")
//                .defaultSuccessUrl("/");
//
//
//
//
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/perform-login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .defaultSuccessUrl("/");





    }
}
