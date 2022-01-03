package com.rec.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rec.client.model.ClientRegistry;
import com.rec.client.repo.ClientRegistryRepository;

@Service
public class ClientRegistryServiceImpl implements ClientRegistryService {
	
	@Autowired
	ClientRegistryRepository cliRepository;

	@Override
	public ClientRegistry addClient(ClientRegistry clientRegistry) {
		clientRegistry = cliRepository.save(clientRegistry); 
		return clientRegistry;
	}

	@Override
	public List<ClientRegistry> listClients() {
		List<ClientRegistry> clientRegistries = (List<ClientRegistry>) cliRepository.findAll();
		return clientRegistries;
	}

	@Override
	public void deleteClient(Long id) {
		cliRepository.deleteById(id);
	}
	

}
