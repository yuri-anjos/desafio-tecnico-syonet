package br.com.syonet.desafiotecnicosyonet.controller;

import br.com.syonet.desafiotecnicosyonet.dto.ClienteCreateDTO;
import br.com.syonet.desafiotecnicosyonet.dto.ClienteDTO;
import br.com.syonet.desafiotecnicosyonet.model.Cliente;
import br.com.syonet.desafiotecnicosyonet.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService service;

	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@GetMapping
	public List<ClienteDTO> findAll() {
		return service.buscarTodos().stream().map(ClienteDTO::new).toList();
	}

	@PostMapping
	public ClienteDTO insert(@RequestBody @Valid ClienteCreateDTO dto) {
		return new ClienteDTO(service.inserir(new Cliente(dto)));
	}
}
