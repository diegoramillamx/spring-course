package com.example.usersapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.usersapp.entities.User;
import com.example.usersapp.services.UserService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping
  @Timed("get.users")
  public ResponseEntity<Page<User>> getUsers(
      @RequestParam(required = false, value = "page", defaultValue = "0") Integer page, 
      @RequestParam(required = false, value = "size", defaultValue = "100") Integer size) {
    return new ResponseEntity<Page<User>>(userService.getUsers(page, size), HttpStatus.OK);
  }

  @GetMapping("/usernames")
  public ResponseEntity<Page<String>> getUsernames(
    @RequestParam(required = false, value = "page", defaultValue = "0") Integer page, 
    @RequestParam(required = false, value = "size", defaultValue = "100") Integer size) {
    return new ResponseEntity<Page<String>>(userService.getUsernames(page, size), HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
    return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
    return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> authenticate(@RequestBody User user) {
    return new ResponseEntity<User>(userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
  }
  
  @DeleteMapping("/username/{username}")
  public ResponseEntity<Void> deleteUserById(@PathVariable("username") String username) {
    userService.deleteUserByUsername(username);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
