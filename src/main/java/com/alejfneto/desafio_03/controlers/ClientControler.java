package com.alejfneto.desafio_03.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejfneto.desafio_03.dto.ClientDTO;
import com.alejfneto.desafio_03.services.ClientService;

@RestController
@RequestMapping (value = "/clients")
public class ClientControler {
	
	@Autowired
	private ClientService service;
	
	@GetMapping (value = "/{id}")
	public ResponseEntity <ClientDTO> findById (@PathVariable Long id){
		ClientDTO clientDTO = service.findById(id);
		return ResponseEntity.ok(clientDTO);		
	}
	

}
