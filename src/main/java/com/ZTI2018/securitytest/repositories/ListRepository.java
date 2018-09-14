package com.ZTI2018.securitytest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ZTI2018.securitytest.models.ItemList;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel="lists", path="lists")
public interface ListRepository  extends JpaRepository<ItemList, Long>{

}
