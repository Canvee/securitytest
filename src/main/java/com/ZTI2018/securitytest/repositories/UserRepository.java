package com.ZTI2018.securitytest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ZTI2018.securitytest.models.AppUser;



public interface UserRepository extends JpaRepository<AppUser, Long>{

	AppUser findByUsername(String username);

    List<AppUser> findByName(String name);
}
