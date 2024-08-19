package br.com.syonet.desafiotecnicosyonet.schedulingtasks;

import br.com.syonet.desafiotecnicosyonet.model.Cliente;
import br.com.syonet.desafiotecnicosyonet.model.Noticia;
import br.com.syonet.desafiotecnicosyonet.service.ClienteService;
import br.com.syonet.desafiotecnicosyonet.service.EmailService;
import br.com.syonet.desafiotecnicosyonet.service.NoticiaService;
import br.com.syonet.desafiotecnicosyonet.util.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.List;

@Component
public class AssinaturaNoticiaTask {

	private static final Logger log = LoggerFactory.getLogger(AssinaturaNoticiaTask.class);
	private final NoticiaService noticiaService;
	private final ClienteService clienteService;
	private final EmailService emailService;
	private final TemplateEngine templateEngine;

	private static final String NOTICIA_EMAIL_TEMPLATE = "noticia";
	private static final String NOTICIA_ASSUNTO = "Notícias do dia!";

	public AssinaturaNoticiaTask(NoticiaService noticiaService, ClienteService clienteService, EmailService emailService, TemplateEngine templateEngine) {
		this.noticiaService = noticiaService;
		this.clienteService = clienteService;
		this.emailService = emailService;
		this.templateEngine = templateEngine;
	}

	@Scheduled(cron = "0 0 8 * * *")
	public void execute() {
		log.info("AssinaturaNoticiaTask: Iniciando a execução da tarefa agendada");
		try {
			List<Noticia> noticiasNaoProcessadas = noticiaService.buscarNoticiasNaoProcessadas();

			if (!noticiasNaoProcessadas.isEmpty()) {
				List<Cliente> clientes = clienteService.buscarTodos();
				prepararEnvioEmail(noticiasNaoProcessadas, clientes);

				log.info("AssinaturaNoticiaTask: Todos os emails foram enviados");
				noticiaService.processarNoticias(noticiasNaoProcessadas.stream().map(Noticia::getId).toList());
			}
		} catch (Exception e) {
			log.error("AssinaturaNoticiaTask: Erro durante a execução da tarefa agendada", e);
		}
	}

	private void prepararEnvioEmail(List<Noticia> noticiasNaoProcessadas, List<Cliente> clientes) {
		LocalDate currentDate = LocalDate.now();

		Context context = new Context();
		context.setVariable("noticias", noticiasNaoProcessadas);

		for (Cliente cliente : clientes) {
			context.setVariable("nome", cliente.getNome());
			context.setVariable("isAniversario", DataUtils.datasNoMesmoDiaEMes(currentDate, cliente.getNascimento()));

			String htmlContent = templateEngine.process(NOTICIA_EMAIL_TEMPLATE, context);

			emailService.enviarMensagemHtml(cliente.getEmail(), NOTICIA_ASSUNTO, htmlContent);
		}
	}
}