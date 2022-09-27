package com.example.usersapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.usersapp.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a FROM Address a WHERE a.profile.user.id = ?1 AND a.profile.id = ?2")
    List<Address> findAddressByUserIdAndProfileId(Integer userId, Integer profileId);
}
