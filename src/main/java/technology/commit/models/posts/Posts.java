package technology.commit.models.posts;

import jakarta.persistence.*;
import lombok.*;
import technology.commit.models.posts.DTOs.DadosPost;
import technology.commit.models.usuario.Usuario;
import java.time.LocalDateTime;


@Table(name = "posts")
@Entity(name = "Posts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPost")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Long idPost;

    @ManyToOne
    @JoinColumn(name = "criador_post_id")
    private Usuario criadorPostId;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Posts(Usuario criadorPost, DadosPost dados) {
        this.criadorPostId = criadorPost;
        this.titulo = dados.titulo();
        this.conteudo = dados.conteudo();
    }

    @PrePersist
    public void prePersist() {
        criadoEm = LocalDateTime.now();
    }

}

