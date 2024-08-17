package br.com.syonet.desafiotecnicosyonet.service;

public interface EmailService {

	void sendHtmlMessage(String to, String subject, String htmlContent);
}
