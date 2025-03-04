package br.dev.mtparreira.tautocronia.repository.pgsql;

import br.dev.mtparreira.tautocronia.entity.pgsql.LivrosEP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRP extends JpaRepository<LivrosEP, Long> {}
