package br.dev.mtparreira.tautocronia.repository.mongo;

import br.dev.mtparreira.tautocronia.entity.mongo.LivrosEM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRM extends MongoRepository<LivrosEM, Long> {}
