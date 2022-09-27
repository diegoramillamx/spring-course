package com.example.usersapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.usersapp.entities.Profile;
import com.example.usersapp.entities.User;
import com.example.usersapp.repositories.ProfileRepository;
import com.example.usersapp.repositories.UserRepository;

@Service
public class ProfileService {
  
  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private UserRepository userRepository;

  public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
    Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
    if (!result.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile with ID %s and User ID %s does not exists", profileId, userId));
    }
    return result.get();
  }

  public Profile createProfile(Integer userId, Profile profile) {
    Optional<User> result = userRepository.findById(userId);
    if (!result.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with ID %s does not exists", userId));
    }
    profile.setUser(result.get());
    return profileRepository.save(profile);
  }
}
