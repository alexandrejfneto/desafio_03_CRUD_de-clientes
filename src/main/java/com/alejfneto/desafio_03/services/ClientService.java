package com.alejfneto.desafio_03.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejfneto.desafio_03.dto.ClientDTO;
import com.alejfneto.desafio_03.entities.Client;
import com.alejfneto.desafio_03.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository service;
	
	@Transactional (readOnly = true)
	public ClientDTO findById (Long id){
		Optional <Client> opt = service.findById(id);
		Client client = opt.get();
		ClientDTO cltDTO = new ClientDTO(client);
		return cltDTO;
	}

}
