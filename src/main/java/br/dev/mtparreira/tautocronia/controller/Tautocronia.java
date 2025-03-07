package br.dev.mtparreira.tautocronia.controller;

import br.dev.mtparreira.tautocronia.entity.mongo.LivrosEM;
import br.dev.mtparreira.tautocronia.service.BibliotecaSRV;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Tautocronia {

  private static Logger logger = LoggerFactory.getLogger(Tautocronia.class);

  @Autowired private BibliotecaSRV bibliotecaSRV;

  @PostMapping("/home")
  public String home() {
    return "It's Alive!!";
  }

  @PostMapping("/gerarlivros")
  public ResponseEntity<String> gerarlivros() {
    Long inicio = System.currentTimeMillis();
    bibliotecaSRV.gerarLivros();
    Long tempo = (System.currentTimeMillis() - inicio) / 1000;
    logger.info("Processo executado em {}s", tempo);
    return ResponseEntity.ok("100000 livros gerados em " + tempo + "s");
  }

  @PostMapping("/transferirlivros")
  public ResponseEntity<String> transferirlivros() {
    Long inicio = System.currentTimeMillis();
    bibliotecaSRV.transferirLivros();
    Long tempo = (System.currentTimeMillis() - inicio) / 1000;
    logger.info("Processo executado em {}s", tempo);
    return ResponseEntity.ok(
        "Livros transferidos da biblioteca PostgreSQL para MongoDB em " + tempo + "s");
  }

  @PostMapping("/lerlivros")
  public ResponseEntity<String> lerlivros() {
    Long inicio = System.currentTimeMillis();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    List<LivrosEM> livros = bibliotecaSRV.lerLivros();
    Long tempoLeitura = (System.currentTimeMillis() - inicio);
    livros.forEach(
        livro -> {
          try {
            String json = objectMapper.writeValueAsString(livro);
            System.out.println(json);
          } catch (Exception e) {
            logger.error("Erro de formato Json: ", e);
          }
        });
    Long tempoEscrita = (System.currentTimeMillis() - inicio);
    logger.info("Processo executado em {}s", ((tempoLeitura + tempoEscrita) / 1000));
    String resposta =
        livros.size()
            + " livros encontrados (leitura "
            + (tempoLeitura / 1000)
            + "s escrita "
            + (tempoEscrita / 1000)
            + "s)";
    return ResponseEntity.ok(resposta);
  }
}
