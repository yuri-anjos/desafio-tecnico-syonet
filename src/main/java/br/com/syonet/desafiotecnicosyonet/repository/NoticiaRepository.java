package br.com.syonet.desafiotecnicosyonet.repository;

import br.com.syonet.desafiotecnicosyonet.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
	List<Noticia> findByProcessado(Boolean processado);
}
