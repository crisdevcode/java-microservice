package com.client.microservice.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.microservice.config.exception.NotFoundException;
import com.client.microservice.model.Client;
import com.client.microservice.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Microservicio de Clientes")
@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@ApiOperation(value = "Retorna todos los clientes existentes", response = List.class)
	@GetMapping
	public ResponseEntity<List<Client>> getClients() {
		List<Client> clients = clientService.getAll();
		if(clients.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clients);		
	}

	@ApiOperation(value = "Retorna un cliente por Id", response = Client.class)
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClient(@ApiParam(value = "Id del cliente a consultar", required = true) @PathVariable("id") Long id){
		Client client = clientService.getClientById(id);
		if(client == null) {
			throw new NotFoundException("Client with Id " + id + " does not exist");
		}
		return ResponseEntity.ok(client);
	}
	
	@ApiOperation(value = "Retorna un cliente basado en el tipo y número de documento")
	@GetMapping("/document/{type}/{number}")
	public ResponseEntity<List<Client>> getClientInfo(@ApiParam(value = "Tipo de documento (C o P)", required = true) @PathVariable String type, @ApiParam(value = "Número de documento", required = true) @PathVariable String number){
		 List<Client> client = clientService.getClientByDocument(type, number);
		 if(client == null) {
			 return ResponseEntity.noContent().build();
		 }
		return ResponseEntity.ok(client);
	}

	@ApiOperation(value = "Crea un nuevo cliente")
	@PostMapping
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		Client newClient = clientService.save(client);
		return ResponseEntity.ok(newClient);
	}
}
