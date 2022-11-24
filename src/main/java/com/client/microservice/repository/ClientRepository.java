package com.client.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.client.microservice.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("select u from Client u where u.documentType=:t and u.documentNumber=:n")
	List<Client> getClientByDocument(@Param("t") String type, @Param("n") String number);
}
