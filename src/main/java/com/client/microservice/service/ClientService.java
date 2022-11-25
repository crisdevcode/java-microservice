package com.client.microservice.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.microservice.model.Client;
import com.client.microservice.repository.ClientRepository;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@PostConstruct
	public void init() {
		// Mock Data
		if(clientRepository.findAll().isEmpty()) {
			Client client = new Client();
			client.setFirstName("Peter");
			client.setLastName("Parker");
			client.setDocumentType("C");
			client.setDocumentNumber("23445322");
			
			save(client);
		}
	}
	
	public List<Client> getAll() {
		return clientRepository.findAll();
	}
	
	public Client getClientById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}
	
	public List<Client> getClientByDocument(String type, String number) {
		
		return clientRepository.getClientByDocument(type, number);
	}
	
	public Client save(Client client) {
		Client newClient = clientRepository.save(client);
		return newClient;
	}
}
