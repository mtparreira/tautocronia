package br.dev.mtparreira.tautocronia.entity.mongo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "livros")
public class LivrosEM {

  @NotNull
  @NotBlank(message = "Título obrigatório")
  private String titulo;

  @NotNull
  @NotBlank(message = "Autor obrigatório")
  private String autor;

  @NotNull private Integer publicacao;
}
