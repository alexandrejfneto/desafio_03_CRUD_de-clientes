package com.alejfneto.desafio_03.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alejfneto.desafio_03.dto.ClientDTO;
import com.alejfneto.desafio_03.entities.Client;
import com.alejfneto.desafio_03.repositories.ClientRepository;
import com.alejfneto.desafio_03.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional (readOnly = true)
	public ClientDTO findById (Long id){
		Optional <Client> opt = repository.findById(id);
		Client client = opt.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
		ClientDTO cltDTO = new ClientDTO(client);
		return cltDTO;
	}
	
	@Transactional (readOnly = true)
	public Page <ClientDTO> findAll(Pageable pageable) {
		Page <Client> listResult = repository.findAll(pageable);
		Page <ClientDTO> listResultFinal = listResult.map(x -> new ClientDTO(x));
		return listResultFinal;		
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO clientDTO) {
		//Metodo Alternativo com instanciacao direta no construtor
		Client client = new Client (null, clientDTO.getName(), clientDTO.getCpf(), clientDTO.getIncome(), clientDTO.getBirthDate(), clientDTO.getChildren());
		client = repository.save(client);
		return new ClientDTO(client);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
		Client clt = repository.getReferenceById(id);
		clt.setName(clientDTO.getName());
		clt.setCpf(clientDTO.getCpf());
		clt.setIncome(clientDTO.getIncome());
		clt.setBirthDate(clientDTO.getBirthDate());
		clt.setChildren(clientDTO.getChildren());
		clt = repository.save(clt);
		return new ClientDTO(clt);
		} catch (EntityNotFoundException e){
			throw new ResourceNotFoundException("Cliente não encontrado!");			
		}
	}

	@Transactional
	public void deletById(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Cliente não encontrado");
		}
		repository.deleteById(id);	
	}
	
}
