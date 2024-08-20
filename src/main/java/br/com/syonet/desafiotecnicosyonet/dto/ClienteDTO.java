package br.com.syonet.desafiotecnicosyonet.dto;

import br.com.syonet.desafiotecnicosyonet.model.Cliente;

import java.time.LocalDate;

public class ClienteDTO {
	private Long id;
	private String nome;
	private String email;
	private LocalDate nascimento;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente entidade) {
		this.id = entidade.getId();
		this.nome = entidade.getNome();
		this.email = entidade.getEmail();
		this.nascimento = entidade.getNascimento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
}
