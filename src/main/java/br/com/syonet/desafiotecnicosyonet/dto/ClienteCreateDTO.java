package br.com.syonet.desafiotecnicosyonet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public class ClienteCreateDTO {

	@NotEmpty
	private String nome;
	@NotEmpty
	@Email
	private String email;
	private LocalDate nascimento;

	public ClienteCreateDTO() {
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}
}
