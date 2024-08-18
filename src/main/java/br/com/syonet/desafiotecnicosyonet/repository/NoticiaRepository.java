package br.com.syonet.desafiotecnicosyonet.repository;

import br.com.syonet.desafiotecnicosyonet.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
	@Query("SELECT n FROM Noticia n WHERE n.processado = FALSE")
	List<Noticia> buscarNoticiasNaoProcessadas(Boolean processado);

	@Query("UPDATE Noticia SET processado = TRUE WHERE id IN ?1")
	@Modifying
	void processarNoticias(List<Long> ids);
}
