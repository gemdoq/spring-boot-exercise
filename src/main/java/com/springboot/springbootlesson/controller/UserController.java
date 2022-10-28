package com.springboot.springbootlesson.controller;

import com.springboot.springbootlesson.dao.UserDao;
import com.springboot.springbootlesson.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/user")
    public User addAndGet() throws SQLException, ClassNotFoundException {
        userDao.add(new User("1","name1","password1"));
        return userDao.findById("1");
    }

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll() throws SQLException, ClassNotFoundException {
        return ResponseEntity.ok().body(userDao.deleteAll());
    }
}