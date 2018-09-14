package com.ZTI2018.securitytest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ZTI2018.securitytest.models.Item;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel="items", path="items")
public interface ItemRepository extends JpaRepository<Item, Long>
{
	// here you can put any declaration like findByTech, getByTech etc and the method will be generated automatically
	// findByIdGreaterThan
	
	// own queries JPQL
	// @Query("from User where tech=? order by name") or ?1 ?2 multiple entries 
	// List<User> findByTechSorted(String tech)
}
