package com.ZTI2018.securitytest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ZTI2018.securitytest.models.AppUser;


@CrossOrigin(origins = "*")
//@CrossOrigin
@RepositoryRestResource(collectionResourceRel="users", path="users")
public interface UserRepository extends JpaRepository<AppUser, Long>{

	AppUser findByUsername(String username);

    List<AppUser> findByMail(String mail);
    
    boolean existsByUsername(String username);
    
    boolean existsByMail(String mail);
}
