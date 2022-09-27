package com.example.usersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.usersapp.entities.Role;
import com.example.usersapp.entities.User;
import com.example.usersapp.entities.UserInRole;
import com.example.usersapp.repositories.RoleRepository;
import com.example.usersapp.repositories.UserInRoleRepository;
import com.example.usersapp.repositories.UserRepository;

@Service
public class UserInRoleService {
  
  @Autowired
  private UserInRoleRepository userInRoleRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  public UserInRole createUserInRole(UserInRole userInRole, Integer userId, Integer roleId) {
    Optional<User> user = userRepository.findById(userId);
    Optional<Role> role = roleRepository.findById(roleId);
    if (!user.isPresent() || !role.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User or role does not exists"));
    }

    userInRole.setUser(user.get());
    userInRole.setRole(role.get());
    return userInRoleRepository.save(userInRole);
  }

  public List<User> getUsersFromAnSpecificRoleName(String roleName) {
    return userInRoleRepository.findUsersByRoleName(roleName);
  }
}
