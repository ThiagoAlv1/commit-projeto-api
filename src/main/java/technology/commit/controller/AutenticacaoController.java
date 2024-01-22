package technology.commit.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technology.commit.models.usuario.DTOs.DadosUsuario;
import technology.commit.models.usuario.Usuario;
import technology.commit.models.repositories.UsuarioRepository;

@RestController
@RequestMapping("/login")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public Usuario cadastrarPost(@RequestBody @Valid DadosUsuario dados){
        var usuario = new Usuario(dados);
        usuarioRepository.save(usuario);
        return usuario;
    }


}
