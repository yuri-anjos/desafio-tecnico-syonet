package br.com.syonet.desafiotecnicosyonet.model;

import br.com.syonet.desafiotecnicosyonet.dto.NoticiaCreateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "noticia")
public class Noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String descricao;

	@Column
	private String url;

	@Column(nullable = false)
	private Boolean processado;

	@PrePersist
	public void prePersist() {
		processado = Boolean.FALSE;
	}

	public Noticia() {
	}

	public Noticia(Long id, String titulo, String descricao, String url, Boolean processado) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
		this.processado = processado;
	}

	public Noticia(NoticiaCreateDTO dto) {
		this.titulo = dto.getTitulo();
		this.descricao = dto.getDescricao();
		this.url = dto.getUrl();
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
