package com.rec.client.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rec.client.model.ClientRegistry;

@Repository
public interface ClientRegistryRepository extends CrudRepository<ClientRegistry, Long>{

	
	
}
