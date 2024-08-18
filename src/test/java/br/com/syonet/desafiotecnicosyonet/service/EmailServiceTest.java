package br.com.syonet.desafiotecnicosyonet.service;

import br.com.syonet.desafiotecnicosyonet.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmailServiceTest {

	@Mock
	private JavaMailSender javaMailSender;

	@InjectMocks
	private EmailServiceImpl emailService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testEnviarMensagemHtmlSuccess() throws MessagingException {
		MimeMessage mimeMessage = mock(MimeMessage.class);
		MimeMessageHelper messageHelper = spy(new MimeMessageHelper(mimeMessage, true));

		when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
		doNothing().when(javaMailSender).send(mimeMessage);

		doAnswer(invocation -> {
			MimeMessageHelper helper = invocation.getArgument(0);
			verify(helper).setTo("test@example.com");
			verify(helper).setSubject("Test Subject");
			verify(helper).setText("<html>Test</html>", true);
			return null;
		}).when(messageHelper).setTo(anyString());

		emailService.enviarMensagemHtml("test@example.com", "Test Subject", "<html>Test</html>");

		verify(javaMailSender).send(mimeMessage);
		verify(messageHelper).setTo("test@example.com");
		verify(messageHelper).setSubject("Test Subject");
		verify(messageHelper).setText("<html>Test</html>", true);
	}
}