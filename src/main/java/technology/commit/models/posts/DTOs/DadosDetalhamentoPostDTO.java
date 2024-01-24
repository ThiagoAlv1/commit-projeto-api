package technology.commit.models.posts.DTOs;


import technology.commit.models.posts.Posts;

public record DadosDetalhamentoPostDTO(
        Long idPost,
        Long criadorPostId,
        String titulo,
        String conteudo
) {
    public DadosDetalhamentoPostDTO(Posts posts) {
        this(posts.getIdPost(), posts.getCriadorPostId().getId(), posts.getTitulo(), posts.getConteudo());
    }

}
