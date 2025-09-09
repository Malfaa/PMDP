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

    public Optional<Usuario> buscaPorId(Long id){ return usuarioRepository.findById(id);}
    public Optional<Usuario> buscaPorEmail(String email){ return usuarioRepository.findByEmail(email);}
    public List<Usuario> buscarTodosUsuarios(){ return usuarioRepository.findAll();}
    public List<Usuario> buscarTodosUsuariosFiltrados(List<Long> ids){ return usuarioRepository.findAllById(ids);}

    public Optional<Usuario> criarUsuario(){} //todo implementar CREATED_AT

    @Transactional
    public Optional<Usuario> editarUsuario(){}

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
