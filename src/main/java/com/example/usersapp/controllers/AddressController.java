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

import com.example.usersapp.entities.Address;
import com.example.usersapp.services.AddressService;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {

  @Autowired
  private AddressService addressService;

  @GetMapping
  public ResponseEntity<List<Address>> findAddressesByProfileAndUserId(@PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId) {
    return new ResponseEntity<List<Address>>(addressService.findAddressesByUserIdAndProfileId(userId, profileId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Address> createAddress(@RequestBody Address address, @PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId) {
    return new ResponseEntity<Address>(addressService.createAddress(address, userId, profileId), HttpStatus.OK);
  }

}
