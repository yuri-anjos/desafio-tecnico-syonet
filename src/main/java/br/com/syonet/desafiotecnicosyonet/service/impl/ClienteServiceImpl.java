package br.com.syonet.desafiotecnicosyonet.service.impl;

import br.com.syonet.desafiotecnicosyonet.model.Cliente;
import br.com.syonet.desafiotecnicosyonet.repository.ClienteRepository;
import br.com.syonet.desafiotecnicosyonet.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository repository;

	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public Cliente inserir(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public List<Cliente> buscarTodos() {
		return repository.findAll();
	}
}
