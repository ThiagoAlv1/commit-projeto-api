package technology.commit.controller;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import technology.commit.models.posts.DTOs.DadosPostDTO;
import technology.commit.models.posts.DTOs.DadosDetalhamentoPostDTO;
import technology.commit.models.posts.DTOs.DadosResgatarPostDTO;
import technology.commit.models.posts.DTOs.DadosRetornoPaginacaoPostDTO;
import technology.commit.models.posts.Posts;
import technology.commit.models.usuario.Usuario;
import technology.commit.models.repositories.PostsRepository;
import technology.commit.models.repositories.UsuarioRepository;


@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPost(@RequestBody @Valid DadosPostDTO dados, UriComponentsBuilder uriComponentsBuilder) {

        Usuario criadorPost = usuarioRepository.findById(dados.criadorPostId())
                .orElseThrow(EntityNotFoundException::new);

        Posts post = new Posts(criadorPost, dados);
        postsRepository.save(post);
        var uri = uriComponentsBuilder.path("/posts/{id}").buildAndExpand(post.getIdPost()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPostDTO(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPost(@PathVariable Long id) {
        var post = postsRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DadosResgatarPostDTO(post));
    }

    @GetMapping("/historico/{id}")
    public ResponseEntity<Page<DadosRetornoPaginacaoPostDTO>> resgatarPostsPorId(@PageableDefault Pageable paginacao, @PathVariable Long id) {

        var usuario = usuarioRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        Page<Posts> pagina = postsRepository.findByCriadorPostId(usuario, paginacao);
        Page<DadosRetornoPaginacaoPostDTO> dtoPagina = pagina.map(DadosRetornoPaginacaoPostDTO::new);

        return ResponseEntity.ok(dtoPagina);
    }
}
