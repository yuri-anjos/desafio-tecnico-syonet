package br.com.syonet.desafiotecnicosyonet.schedulingtasks;

import br.com.syonet.desafiotecnicosyonet.model.Noticia;
import br.com.syonet.desafiotecnicosyonet.service.ClienteService;
import br.com.syonet.desafiotecnicosyonet.service.EmailService;
import br.com.syonet.desafiotecnicosyonet.service.NoticiaService;
import br.com.syonet.desafiotecnicosyonet.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

@Component
public class NewsletterTask {

	private static final Logger log = LoggerFactory.getLogger(NewsletterTask.class);
	private final NoticiaService noticiaService;
	private final ClienteService clienteService;
	private final EmailService emailService;
	private final TemplateEngine templateEngine;

	private static final String NEWSLETTER_TEMPLATE = "newsletter";

	public NewsletterTask(NoticiaService noticiaService, ClienteService clienteService, EmailService emailService, TemplateEngine templateEngine) {
		this.noticiaService = noticiaService;
		this.clienteService = clienteService;
		this.emailService = emailService;
		this.templateEngine = templateEngine;
	}

	@Scheduled(fixedRate = 5000) // Executa a cada 10 segundos
//	@Scheduled(cron = "0 0 8 * * *") // Executa as 08h todos os dias
	public void execute() {
		var noticiasNaoProcessadas = noticiaService.findAllNaoProcessado();

		if (!noticiasNaoProcessadas.isEmpty()) {
			var context = new Context();
			context.setVariable("noticias", noticiasNaoProcessadas);

			var currentdate = LocalDate.now();
			var clientes = clienteService.findAll();

			clientes.forEach(cliente -> {
				context.setVariable("nome", cliente.getNome());
				context.setVariable("isAniversario", DateUtils.areDatesInSameDayAndMonth(currentdate, cliente.getNascimento()));

				var htmlContent = templateEngine.process(NEWSLETTER_TEMPLATE, context);

				emailService.sendHtmlMessage(
						cliente.getEmail(),
						"Noticias do dia!",
						htmlContent);
			});

			log.info("All emails have been sent!");
			noticiaService.processaNoticias(noticiasNaoProcessadas.stream().map(Noticia::getId).toList());
		}
	}
}
