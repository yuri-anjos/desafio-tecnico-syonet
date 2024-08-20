package br.com.syonet.desafiotecnicosyonet.controller;

import br.com.syonet.desafiotecnicosyonet.dto.NoticiaCreateDTO;
import br.com.syonet.desafiotecnicosyonet.dto.NoticiaDTO;
import br.com.syonet.desafiotecnicosyonet.model.Noticia;
import br.com.syonet.desafiotecnicosyonet.service.NoticiaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/noticias")
public class NoticiaController {

	private final NoticiaService service;

	public NoticiaController(NoticiaService service) {
		this.service = service;
	}

	@GetMapping
	public List<NoticiaDTO> buscarNoticiasNaoProcessadas() {
		return service.buscarNoticiasNaoProcessadas().stream().map(NoticiaDTO::new).toList();
	}

	@PostMapping
	public NoticiaDTO inserir(@RequestBody @Valid NoticiaCreateDTO dto) {
		return new NoticiaDTO(service.inserir(new Noticia(dto)));
	}
}
