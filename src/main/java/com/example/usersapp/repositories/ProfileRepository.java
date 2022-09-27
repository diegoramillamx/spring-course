package com.example.usersapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.usersapp.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    @Query("SELECT p FROM Profile p WHERE p.user.id=?1 AND p.id=?2")
    Optional<Profile> findByUserIdAndProfileId(Integer userId, Integer profileId);
    
}
