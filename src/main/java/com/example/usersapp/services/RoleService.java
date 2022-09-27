package com.example.usersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.usersapp.entities.Role;
import com.example.usersapp.repositories.RoleRepository;

@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;
  

  public List<Role> getRoles() {
    return roleRepository.findAll();
  }

  public Role createRole(Role role) {
    return roleRepository.save(role);
  }

  public Role updateRole(Integer roleId, Role role) {
    Optional<Role> result = roleRepository.findById(roleId);
    if (!result.isPresent()) {
      new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with ID %s not found", roleId));
    }
    return roleRepository.save(role);
  }

  public void deleteRole(Integer roleId) {
    Optional<Role> result = roleRepository.findById(roleId);
    if (!result.isPresent()) {
      new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with ID %s not found", roleId));
    }
    roleRepository.deleteById(roleId);
  }
}
