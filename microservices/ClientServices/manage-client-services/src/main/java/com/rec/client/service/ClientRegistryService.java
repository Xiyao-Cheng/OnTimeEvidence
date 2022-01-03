package com.rec.client.service;

import java.util.List;

import com.rec.client.model.ClientRegistry;

public interface ClientRegistryService {

	ClientRegistry addClient(ClientRegistry clientRegistry);

	List<ClientRegistry> listClients();

	void deleteClient(Long id);

}
