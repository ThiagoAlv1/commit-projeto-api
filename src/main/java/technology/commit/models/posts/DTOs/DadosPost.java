package technology.commit.models.posts.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import technology.commit.models.usuario.Usuario;

public record DadosPost(
        @Valid
        @NotNull
        Long criadorPostId,
        @NotBlank
        String titulo,
        @NotBlank
        String conteudo
) {

}
