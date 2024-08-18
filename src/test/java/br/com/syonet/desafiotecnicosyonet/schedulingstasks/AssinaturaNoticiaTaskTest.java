package br.com.syonet.desafiotecnicosyonet.schedulingstasks;


import br.com.syonet.desafiotecnicosyonet.model.Cliente;
import br.com.syonet.desafiotecnicosyonet.model.Noticia;
import br.com.syonet.desafiotecnicosyonet.schedulingtasks.AssinaturaNoticiaTask;
import br.com.syonet.desafiotecnicosyonet.service.ClienteService;
import br.com.syonet.desafiotecnicosyonet.service.EmailService;
import br.com.syonet.desafiotecnicosyonet.service.NoticiaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class AssinaturaNoticiaTaskTest {

	@Mock
	private NoticiaService noticiaService;

	@Mock
	private ClienteService clienteService;

	@Mock
	private EmailService emailService;

	@Mock
	private TemplateEngine templateEngine;

	@InjectMocks
	private AssinaturaNoticiaTask assinaturaNoticiaTask;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void executeSucessoTest() {
		String emailTemplate = "noticia";
		Noticia noticia1 = new Noticia(1L, "Notícia 1", "Descricao 1", "url", Boolean.FALSE);
		Noticia noticia2 = new Noticia(2L, "Notícia 2", "Descricao 2", "url", Boolean.FALSE);
		Cliente cliente = new Cliente(1L, "Cliente", "cliente@example.com", LocalDate.now());

		when(noticiaService.buscarNoticiasNaoProcessadas()).thenReturn(Arrays.asList(noticia1, noticia2));
		when(clienteService.buscarTodos()).thenReturn(List.of(cliente));
		when(templateEngine.process(eq(emailTemplate), any(Context.class))).thenReturn("<html>Test Content</html>");
		ArgumentCaptor<Context> contextCaptor = ArgumentCaptor.forClass(Context.class);

		assinaturaNoticiaTask.execute();

		verify(emailService).enviarMensagemHtml("cliente@example.com", "Notícias do dia!", "<html>Test Content</html>");
		verify(noticiaService).processarNoticias(Arrays.asList(1L, 2L));
		verify(templateEngine).process(eq(emailTemplate), contextCaptor.capture());
		Context capturedContext = contextCaptor.getValue();

		assertNotNull(capturedContext.getVariable("noticias"));
		List<Noticia> noticias = (List<Noticia>) capturedContext.getVariable("noticias");
		assertTrue(noticias.containsAll(Arrays.asList(noticia1, noticia2)));
		assertEquals("Cliente", capturedContext.getVariable("nome"));
		assertTrue((Boolean) capturedContext.getVariable("isAniversario"));
	}

	@Test
	void executeSemNoticiasTest() {
		when(noticiaService.buscarNoticiasNaoProcessadas()).thenReturn(List.of());

		assinaturaNoticiaTask.execute();

		verifyNoInteractions(clienteService);
		verifyNoInteractions(emailService);
		verify(noticiaService).buscarNoticiasNaoProcessadas();
		verify(noticiaService, never()).processarNoticias(anyList());
	}

	@Test
	void executeErroTest() {
		String assunto = "Notícias do dia!";
		String htmlContent = "<html>Test Content</html>";
		Noticia noticia1 = new Noticia(1L, "Notícia 1", "Descricao 1", "url", Boolean.FALSE);
		Noticia noticia2 = new Noticia(2L, "Notícia 2", "Descricao 2", "url", Boolean.FALSE);
		Cliente cliente = new Cliente(1L, "Cliente", "cliente@example.com", LocalDate.now());

		when(noticiaService.buscarNoticiasNaoProcessadas()).thenReturn(Arrays.asList(noticia1, noticia2));
		when(clienteService.buscarTodos()).thenReturn(List.of(cliente));
		when(templateEngine.process(anyString(), any(Context.class))).thenReturn(htmlContent);
		doThrow(new RuntimeException("Error"))
				.when(emailService).enviarMensagemHtml(anyString(), anyString(), anyString());

		assinaturaNoticiaTask.execute();

		verify(emailService).enviarMensagemHtml(cliente.getEmail(), assunto, htmlContent);
		verify(noticiaService, never()).processarNoticias(anyList());
	}
}