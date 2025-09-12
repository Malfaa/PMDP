package com.malfaa.pmdp.service;

import com.malfaa.pmdp.model.User;
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
    public Optional<User> buscaPorId(Long id){ return usuarioRepository.findById(id);}
    public Optional<User> buscaPorEmail(String email){ return usuarioRepository.findByEmail(email);}
    public List<User> buscarTodosUsuarios(){ return usuarioRepository.findAll();}
    public List<User> buscarTodosUsuariosFiltrados(List<Long> ids){ return usuarioRepository.findAllById(ids);}

    public User criarUsuario(User novoUser){
        Optional<User> userExist = usuarioRepository.findByEmail(novoUser.getEmail());
        if (userExist.isPresent()){
            throw new IllegalArgumentException("Usuário com este e-mail já existe!");
        }

        return usuarioRepository.save(novoUser);
    }

    @Transactional
    public User editarUsuario(Long usuarioAntigo, User userAtualizado){
        User user = usuarioRepository.findById(usuarioAntigo).orElseThrow(
                () -> new RuntimeException("Usuario do ID: "+ usuarioAntigo + " não encontrado")
        );

        Optional.ofNullable(usuarioAtualizado.getNome()).ifPresent(usuario::setNome);
        Optional.ofNullable(usuarioAtualizado.getSenha()).ifPresent(
                senha -> usuario.setSenha(passwordEncoder.encode(senha))
        );
        Optional.ofNullable(usuarioAtualizado.getEmail()).ifPresent(usuario::setEmail);
        Optional.ofNullable(usuarioAtualizado.getTipo()).ifPresent(usuario::setTipo);
        return usuarioRepository.save(user);
    }

    public void deletarUsuario(User user){ usuarioRepository.delete(user);}
    public void deletarUsuarioPorId(Long id){ usuarioRepository.deleteById(id);}
    public void deletarUsuarioPorEmail(String email){
        User user = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("E-mail não encontrado")
        );
        usuarioRepository.deleteById(user.getId());
    }
    public void deletarTodosUsuarios(){ usuarioRepository.deleteAll();}
}
