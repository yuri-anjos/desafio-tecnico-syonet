package br.com.syonet.desafiotecnicosyonet.service.impl;

import br.com.syonet.desafiotecnicosyonet.model.Noticia;
import br.com.syonet.desafiotecnicosyonet.repository.NoticiaRepository;
import br.com.syonet.desafiotecnicosyonet.service.NoticiaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaServiceImpl implements NoticiaService {

	private final NoticiaRepository repository;

	public NoticiaServiceImpl(NoticiaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Noticia inserir(Noticia noticia) {
		return repository.save(noticia);
	}

	@Override
	public List<Noticia> buscarNoticiasNaoProcessadas() {
		return repository.buscarNoticiasNaoProcessadas(Boolean.FALSE);
	}

	@Override
	@Transactional
	public void processarNoticias(List<Long> ids) {
		repository.processarNoticias(ids);
	}
}
