package technology.commit.controller;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import technology.commit.models.posts.DTOs.DadosPost;
import technology.commit.models.posts.DTOs.DadosDetalhamentoPost;
import technology.commit.models.posts.DTOs.DadosResgatarPost;
import technology.commit.models.posts.DTOs.DadosRetornoPaginacao;
import technology.commit.models.posts.Posts;
import technology.commit.models.usuario.Usuario;
import technology.commit.models.repositories.PostsRepository;
import technology.commit.models.repositories.UsuarioRepository;

import java.util.UUID;


@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPost(@RequestBody @Valid DadosPost dados, UriComponentsBuilder uriComponentsBuilder) {

        Usuario criadorPost = usuarioRepository.findById(dados.criadorPostId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Posts post = new Posts(criadorPost, dados);
        postsRepository.save(post);
        var uri = uriComponentsBuilder.path("/posts/{id}").buildAndExpand(post.getIdPost()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPost(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPost(@PathVariable Long id) {
        var post = postsRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosResgatarPost(post));
    }

    @GetMapping("/historico/{id}")
    public ResponseEntity<Page<DadosRetornoPaginacao>> resgatarPostsPorId(@PageableDefault Pageable paginacao, @PathVariable Long id) {

        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        Page<Posts> page = postsRepository.findByCriadorPostId(usuario, paginacao);
        Page<DadosRetornoPaginacao> dtoPage = page.map(DadosRetornoPaginacao::new);

        return ResponseEntity.ok(dtoPage);
    }
}
