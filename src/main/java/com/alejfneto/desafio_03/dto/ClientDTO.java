package com.alejfneto.desafio_03.dto;

import java.time.LocalDate;

import com.alejfneto.desafio_03.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ClientDTO {
	
	private Long id;
	@NotBlank (message = "Nome não pode ser vazio!")
	private String name;
	private String cpf;
	private Double income;
	@PastOrPresent (message = "Data de nascimento não pode ser futura!")
	private LocalDate birthDate;
	private Integer children;
	
	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO (Client client) {
		id = client.getId();
		name = client.getName();
		cpf = client.getCpf();
		income = client.getIncome();
		birthDate = client.getBirthDate();
		children = client.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return "ClientDTO [id=" + id + ", name=" + name + ", cpf=" + cpf + ", income=" + income + ", birthDate="
				+ birthDate + ", children=" + children + "]";
	}
	
}
