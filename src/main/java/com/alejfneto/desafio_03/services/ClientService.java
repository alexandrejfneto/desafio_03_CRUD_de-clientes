package com.alejfneto.desafio_03.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejfneto.desafio_03.dto.ClientDTO;
import com.alejfneto.desafio_03.entities.Client;
import com.alejfneto.desafio_03.repositories.ClientRepository;
import com.alejfneto.desafio_03.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional (readOnly = true)
	public ClientDTO findById (Long id){
		Optional <Client> opt = repository.findById(id);
		Client client = opt.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
		ClientDTO cltDTO = new ClientDTO(client);
		return cltDTO;
	}

}
