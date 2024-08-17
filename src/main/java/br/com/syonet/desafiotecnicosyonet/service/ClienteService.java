package br.com.syonet.desafiotecnicosyonet.service;

import br.com.syonet.desafiotecnicosyonet.model.Cliente;

import java.util.List;

public interface ClienteService {

	Cliente insert(Cliente cliente);
	List<Cliente> findAll();

}
