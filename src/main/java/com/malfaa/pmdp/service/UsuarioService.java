package com.malfaa.pmdp.service;

import com.malfaa.pmdp.model.Usuario;
import com.malfaa.pmdp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //RETORNA DA MÁQUINA A ZONA PARA MEDIR CORRETAMENTE O FUSO HORÁRIO
    public Optional<Usuario> buscaPorId(Long id){ return usuarioRepository.findById(id);}
    public Optional<Usuario> buscaPorEmail(String email){ return usuarioRepository.findByEmail(email);}
    public List<Usuario> buscarTodosUsuarios(){ return usuarioRepository.findAll();}
    public List<Usuario> buscarTodosUsuariosFiltrados(List<Long> ids){ return usuarioRepository.findAllById(ids);}

    public Usuario criarUsuario(Usuario novoUsuario){
        Optional<Usuario> userExist = usuarioRepository.findByEmail(novoUsuario.getEmail());
        if (userExist.isPresent()){
            throw new IllegalArgumentException("Usuário com este e-mail já existe!");
        }

        return usuarioRepository.save(novoUsuario);
    }

    @Transactional
    public Usuario editarUsuario(Long usuarioAntigo, Usuario usuarioAtualizado){
        Usuario usuario = usuarioRepository.findById(usuarioAntigo).orElseThrow(
                () -> new RuntimeException("Usuario do ID: "+ usuarioAntigo + " não encontrado")
        );

        Optional.ofNullable(usuarioAtualizado.getNome()).ifPresent(usuario::setNome);
        Optional.ofNullable(usuarioAtualizado.getSenha()).ifPresent(
            senha -> usuario.setSenha(passwordEncoder.encode(senha))
            );
        Optional.ofNullable(usuarioAtualizado.getEmail()).ifPresent(usuario::setEmail);
        Optional.ofNullable(usuarioAtualizado.getTipo()).ifPresent(usuario::setTipo);

        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Usuario usuario){ usuarioRepository.delete(usuario);}
    public void deletarUsuarioPorId(Long id){ usuarioRepository.deleteById(id);}
    public void deletarUsuarioPorEmail(String email){
        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("E-mail não encontrado")
        );
        usuarioRepository.deleteById(user.getId());
    }
    public void deletarTodosUsuarios(){ usuarioRepository.deleteAll();}
}
