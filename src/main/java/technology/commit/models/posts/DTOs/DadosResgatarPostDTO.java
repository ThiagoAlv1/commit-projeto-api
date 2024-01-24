package technology.commit.models.posts.DTOs;

import technology.commit.models.posts.Posts;

import java.time.LocalDateTime;

public record DadosResgatarPostDTO(
        Long idPost,
        Long criadorPostId,
        String titulo,
        String conteudo,
        LocalDateTime criadoEm
) {

    public DadosResgatarPostDTO(Posts posts) {
        this (  posts.getIdPost(),
                posts.getCriadorPostId().getId(),
                posts.getTitulo(),
                posts.getConteudo(),
                posts.getCriadoEm());
    }

}
