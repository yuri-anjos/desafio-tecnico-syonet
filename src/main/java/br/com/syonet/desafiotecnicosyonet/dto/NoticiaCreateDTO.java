package br.com.syonet.desafiotecnicosyonet.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class NoticiaCreateDTO {
	@NotEmpty
	private String titulo;
	@NotEmpty
	private String descricao;
	@Pattern(regexp = "^(http|https)://.*$",
			message = "URL inválida!",
			flags = {Pattern.Flag.CASE_INSENSITIVE})
	private String url;

	public NoticiaCreateDTO() {
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUrl() {
		return url;
	}
}
