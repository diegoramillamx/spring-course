package com.example.usersapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usersapp.entities.User;
import com.example.usersapp.entities.UserInRole;
import com.example.usersapp.services.UserInRoleService;

@RestController
@RequestMapping("/usersInRoles")
public class UserInRoleController {

  @Autowired
  private UserInRoleService userInRoleService;

  @PostMapping("/users/{userId}/roles/{roleId}")
  public ResponseEntity<UserInRole> createUserInRole(@RequestBody UserInRole userInRole, @PathVariable("userId") Integer userId, @PathVariable("roleId") Integer roleId) {
    return new ResponseEntity<UserInRole>(userInRoleService.createUserInRole(userInRole, userId, roleId), HttpStatus.CREATED);
  }

  @GetMapping("/users/roles/{roleName}")
  public ResponseEntity<List<User>> getUsersFromAnSpecificRoleName(@PathVariable("roleName") String roleName) {
    return new ResponseEntity<List<User>>(userInRoleService.getUsersFromAnSpecificRoleName(roleName), HttpStatus.CREATED);
  }
}
