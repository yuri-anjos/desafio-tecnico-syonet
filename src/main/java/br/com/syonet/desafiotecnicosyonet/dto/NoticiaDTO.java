package br.com.syonet.desafiotecnicosyonet.dto;

import br.com.syonet.desafiotecnicosyonet.model.Noticia;

public class NoticiaDTO {
	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	private Boolean processado;

	public NoticiaDTO() {
	}

	public NoticiaDTO(Noticia entidade) {
		this.id = entidade.getId();
		this.titulo = entidade.getTitulo();
		this.descricao = entidade.getDescricao();
		this.url = entidade.getUrl();
		this.processado = entidade.getProcessado();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getProcessado() {
		return processado;
	}

	public void setProcessado(Boolean processado) {
		this.processado = processado;
	}
}
