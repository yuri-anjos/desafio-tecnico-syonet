package br.com.syonet.desafiotecnicosyonet.repository;

import br.com.syonet.desafiotecnicosyonet.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
