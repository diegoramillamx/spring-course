package com.example.usersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.usersapp.entities.Address;
import com.example.usersapp.entities.Profile;
import com.example.usersapp.repositories.AddressRepository;
import com.example.usersapp.repositories.ProfileRepository;

@Service
public class AddressService {

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private ProfileRepository profileRepository;

  public List<Address> findAddressesByUserIdAndProfileId(Integer userId, Integer profileId) {
    return addressRepository.findAddressByUserIdAndProfileId(userId, profileId);
  }

  public Address createAddress(Address address, Integer userId, Integer profileId) {
    Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
    if (!result.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Address with USER ID %d and Profile ID %d not exists", userId, profileId));
    }
    Profile profile = result.get();
    address.setProfile(profile);
    return addressRepository.save(address);
  }
}
