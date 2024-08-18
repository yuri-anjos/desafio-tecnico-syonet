package br.com.syonet.desafiotecnicosyonet.service;

public interface EmailService {

	void enviarMensagemHtml(String to, String subject, String htmlContent);
}
