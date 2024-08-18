package br.com.syonet.desafiotecnicosyonet.controller;

import br.com.syonet.desafiotecnicosyonet.model.Noticia;
import br.com.syonet.desafiotecnicosyonet.service.NoticiaService;
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
	public List<Noticia> buscarNoticiasNaoProcessadas(){
		return service.buscarNoticiasNaoProcessadas();
	}

	@PostMapping
	public Noticia inserir(@RequestBody Noticia noticia){
		return service.inserir(noticia);
	}
}
