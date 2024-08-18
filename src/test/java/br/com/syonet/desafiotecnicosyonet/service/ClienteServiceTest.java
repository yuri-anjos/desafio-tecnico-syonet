package br.com.syonet.desafiotecnicosyonet.service;

import br.com.syonet.desafiotecnicosyonet.model.Cliente;
import br.com.syonet.desafiotecnicosyonet.repository.ClienteRepository;
import br.com.syonet.desafiotecnicosyonet.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClienteServiceTest {
	@Mock
	private ClienteRepository repository;

	@InjectMocks
	private ClienteServiceImpl service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void inserirTest() {
		Cliente cliente = new Cliente();

		when(repository.save(cliente)).thenReturn(cliente);

		Cliente resultado = service.inserir(cliente);

		assertNotNull(resultado);
		assertEquals(cliente, resultado);
		verify(repository).save(cliente);
	}

	@Test
	void buscarTodosTest() {
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

		when(repository.findAll()).thenReturn(clientes);

		List<Cliente> result = service.buscarTodos();

		assertNotNull(result);
		assertEquals(2, result.size());
		verify(repository).findAll();
	}
}
