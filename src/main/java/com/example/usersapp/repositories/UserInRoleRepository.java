package com.example.usersapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.usersapp.entities.User;
import com.example.usersapp.entities.UserInRole;

public interface UserInRoleRepository extends JpaRepository<UserInRole, Integer> {

  @Query("SELECT u.user FROM UserInRole u WHERE u.role.name = ?1")
  public List<User> findUsersByRoleName(String roleName);
}
