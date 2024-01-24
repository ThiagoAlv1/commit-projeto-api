package technology.commit.models.posts.DTOs;


import technology.commit.models.posts.Posts;

import java.util.Optional;

public record DadosDetalhamentoPost(
        Long idPost,
        Long criadorPostId,
        String titulo,
        String conteudo
) {
    public DadosDetalhamentoPost (Posts posts) {
        this(posts.getIdPost(), posts.getCriadorPostId().getId(), posts.getTitulo(), posts.getConteudo());
    }

}
