package br.dev.mtparreira.tautocronia.controller;

import br.dev.mtparreira.tautocronia.service.BibliotecaSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Tautocronia {

  @Autowired private BibliotecaSRV bibliotecaSRV;

  @PostMapping("/home")
  public String home() {
    return "It's Alive!!";
  }

  @PostMapping("/gerarlivros")
  public ResponseEntity<String> gerarlivros() {
    bibliotecaSRV.gerarLivros();
    return ResponseEntity.ok("1000 livros gerados");
  }

  @PostMapping("/transferirlivros")
  public ResponseEntity<String> transferirlivros() {
    bibliotecaSRV.transferirLivros();
    return ResponseEntity.ok("Livros transferidos da biblioteca PostgreSQL para MongoDB");
  }

  @PostMapping("/lerlivros")
  public String lerlivros() {
    return bibliotecaSRV.lerLivros().toString();
  }
}
