package br.com.syonet.desafiotecnicosyonet.service;

import br.com.syonet.desafiotecnicosyonet.model.Noticia;

import java.util.List;

public interface NoticiaService {
	Noticia insert(Noticia noticia);
	List<Noticia> findAllNaoProcessado();
	void processaNoticias(List<Long> ids);
}
