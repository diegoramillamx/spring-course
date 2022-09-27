package com.example.usersapp.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.usersapp.entities.User;
import com.example.usersapp.repositories.UserRepository;

@Service
public class UserService {

  private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;

  public Page<User> getUsers(int page, int size) {
    return userRepository.findAll(PageRequest.of(page, size));
  }

  public Page<String> getUsernames(int page, int size) {
    return userRepository.findUsernames(PageRequest.of(page, size));
  }

  public User getUserById(Integer userId) {
    Optional<User> result = userRepository.findById(userId);
    if (!result.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with ID %s not found", userId));
    }

    return result.get();
  }

  @Cacheable("users")
  public User getUserByUsername(String username) {
    LOG.info("Getting user by username {}", username);
    Optional<User> result = userRepository.findByUsername(username);
    if (!result.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with username %s not found", username));
    }

    return result.get();
  }

  @CacheEvict("users")
  public void deleteUserByUsername(String username) {
    User user = getUserByUsername(username);
    userRepository.delete(user);
  }

  public User getUserByUsernameAndPassword(String username, String password) {
    Optional<User> result = userRepository.findByUsernameAndPassword(username, password);
    if (!result.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with username %s not found", username));
    }

    return result.get();
  }
}
