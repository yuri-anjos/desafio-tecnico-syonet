package br.com.syonet.desafiotecnicosyonet.service;

import br.com.syonet.desafiotecnicosyonet.model.Noticia;

import java.util.List;

public interface NoticiaService {
	Noticia inserir(Noticia noticia);

	List<Noticia> buscarNoticiasNaoProcessadas();

	void processarNoticias(List<Long> ids);
}
