package technology.commit.models.posts.DTOs;

import technology.commit.models.posts.Posts;

import java.time.LocalDateTime;

public record DadosRetornoPaginacao(
        Long idPost,
        Long criadorPostId,
        String criadorPostLogin,
        String titulo,
        String conteudo,
        LocalDateTime criadoEm
) {
    public DadosRetornoPaginacao(Posts posts) {
        this(posts.getIdPost(),
                posts.getCriadorPostId().getId(),
                posts.getCriadorPostId().getLogin(),
                posts.getTitulo(),
                posts.getConteudo(),
                posts.getCriadoEm());
    }
}

