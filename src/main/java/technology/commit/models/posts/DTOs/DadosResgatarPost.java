package technology.commit.models.posts.DTOs;

import org.springframework.data.domain.Page;
import technology.commit.models.posts.Posts;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public record DadosResgatarPost(
        Long idPost,
        Long criadorPostId,
        String titulo,
        String conteudo,
        LocalDateTime criadoEm
) {

    public DadosResgatarPost(Posts posts) {
        this (  posts.getIdPost(),
                posts.getCriadorPostId().getId(),
                posts.getTitulo(),
                posts.getConteudo(),
                posts.getCriadoEm());
    }

}
