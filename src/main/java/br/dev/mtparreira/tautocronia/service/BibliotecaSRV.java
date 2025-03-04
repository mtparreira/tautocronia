package br.dev.mtparreira.tautocronia.service;

import br.dev.mtparreira.tautocronia.entity.mongo.LivrosEM;
import br.dev.mtparreira.tautocronia.entity.pgsql.LivrosEP;
import br.dev.mtparreira.tautocronia.repository.mongo.BibliotecaRM;
import br.dev.mtparreira.tautocronia.repository.pgsql.BibliotecaRP;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibliotecaSRV {

  @Autowired private BibliotecaRM bibliotecaRM;

  @Autowired private BibliotecaRP bibliotecaRP;

  public void gerarLivros() {
    List<LivrosEP> livros = new ArrayList<>();
    for (int i = 1; i <= 1000; i++) {
      livros.add(new LivrosEP(null, "Livro " + i, "Autor " + i, 2000 + i));
    }
    bibliotecaRP.saveAll(livros);
  }

  public void transferirLivros() {
    List<LivrosEP> livrosEP = bibliotecaRP.findAll();
    Integer totalBatch = 100;
    for (Integer i = 0; i < livrosEP.size(); i += totalBatch) {
      List<LivrosEM> registros =
          livrosEP.subList(i, Math.min(i + totalBatch, livrosEP.size())).stream()
              .map(
                  livro -> {
                    LivrosEM novoLivro = new LivrosEM();
                    novoLivro.setTitulo(livro.getTitulo());
                    novoLivro.setAutor(livro.getAutor());
                    novoLivro.setPublicacao(livro.getPublicacao());
                    return novoLivro;
                  })
              .collect(Collectors.toList());
      bibliotecaRM.saveAll(registros);
    }
  }

  public List<LivrosEM> lerLivros() {
    return bibliotecaRM.findAll();
  }
}
