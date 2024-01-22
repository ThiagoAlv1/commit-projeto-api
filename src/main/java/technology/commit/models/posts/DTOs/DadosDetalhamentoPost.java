package technology.commit.models.posts.DTOs;


public record RespostaPost(
        Long idPost,
        Long criadorPostId,
        String titulo,
        String conteudo
) {

}
