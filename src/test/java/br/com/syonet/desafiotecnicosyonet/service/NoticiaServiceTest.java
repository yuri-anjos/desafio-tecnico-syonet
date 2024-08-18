package br.com.syonet.desafiotecnicosyonet.service;

import br.com.syonet.desafiotecnicosyonet.model.Noticia;
import br.com.syonet.desafiotecnicosyonet.repository.NoticiaRepository;
import br.com.syonet.desafiotecnicosyonet.service.impl.NoticiaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NoticiaServiceTest {
	@Mock
	private NoticiaRepository repository;

	@InjectMocks
	private NoticiaServiceImpl service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void inserirTest() {
		Noticia noticia = new Noticia();

		when(repository.save(noticia)).thenReturn(noticia);

		Noticia result = service.inserir(noticia);

		assertNotNull(result);
		assertEquals(noticia, result);
		verify(repository).save(noticia);
	}

	@Test
	void buscarNoticiasNaoProcessadasTest() {
		Noticia noticia1 = new Noticia();
		Noticia noticia2 = new Noticia();
		List<Noticia> noticias = Arrays.asList(noticia1, noticia2);

		when(repository.buscarNoticiasNaoProcessadas(ArgumentMatchers.anyBoolean())).thenReturn(noticias);

		List<Noticia> result = service.buscarNoticiasNaoProcessadas();

		assertNotNull(result);
		assertEquals(2, result.size());
		verify(repository).buscarNoticiasNaoProcessadas(ArgumentMatchers.anyBoolean());
	}

	@Test
	@Transactional
	void processaNoticiasTest() {
		List<Long> noticias = Arrays.asList(1L, 2L, 3L);

		doNothing().when(repository).processarNoticias(noticias);

		service.processarNoticias(noticias);

		verify(repository).processarNoticias(noticias);
	}
}
