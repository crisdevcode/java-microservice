package com.client.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.microservice.config.exception.BadRequestException;
import com.client.microservice.config.exception.NotFoundException;
import com.client.microservice.model.Client;
import com.client.microservice.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<Client>> getClients() {
		List<Client> clients = clientService.getAll();
		if(clients.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clients);		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getClient(@PathVariable("id") Long id){
		Client client = clientService.getClientById(id);
		if(client == null) {
			throw new NotFoundException("Client with Id " + id + " does not exist");
		}
		return ResponseEntity.ok(client);
	}
	
	@GetMapping("/document/{type}/{number}")
	public ResponseEntity<List<Client>> getClientInfo(@PathVariable String type, @PathVariable String number){
		 List<Client> client = clientService.getClientByDocument(type, number);
		 if(client == null) {
			 return ResponseEntity.noContent().build();
		 }
		return ResponseEntity.ok(client);
	}
	
	@PostMapping
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		Client newClient = clientService.save(client);
		return ResponseEntity.ok(newClient);
	}
	
}
