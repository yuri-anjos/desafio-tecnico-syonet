package br.com.syonet.desafiotecnicosyonet.service;

import br.com.syonet.desafiotecnicosyonet.model.Cliente;

import java.util.List;

public interface ClienteService {

	Cliente inserir(Cliente cliente);

	List<Cliente> buscarTodos();
}
