package com.malfaa.pmdp.service;

import com.malfaa.pmdp.model.User;
import com.malfaa.pmdp.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //RETORNA DA MÁQUINA A ZONA PARA MEDIR CORRETAMENTE O FUSO HORÁRIO
    @Transactional(readOnly = true)
    public Optional<User> searchById(Long id){ return usuarioRepository.findById(id);}

    @Transactional(readOnly = true)
    public Optional<User> searchByEmail(String email){ return usuarioRepository.findByEmail(email);}

    @Transactional(readOnly = true)
    public List<User> searchAll(){ return usuarioRepository.findAll();}

    @Transactional(readOnly = true)
    public List<User> searchByFilteredUsers(List<Long> ids){ return usuarioRepository.findAllById(ids);}

    @Transactional
    public User createUser(User novoUser){
        Optional<User> userExist = usuarioRepository.findByEmail(novoUser.getEmail());
        if (userExist.isPresent()){
            throw new IllegalArgumentException("Usuário com este e-mail já existe!");
        }
        String senhaCriptografada = passwordEncoder.encode(novoUser.getPassword());
        novoUser.setPassword(senhaCriptografada);

        return usuarioRepository.save(novoUser);
    }

    @Transactional
    public User editUser(Long usuarioAntigo, User userAtualizado){
        User user = usuarioRepository.findById(usuarioAntigo).orElseThrow(
                () -> new RuntimeException("Usuario do ID: "+ usuarioAntigo + " não encontrado")
        );

        Optional.ofNullable(userAtualizado.getName()).ifPresent(user::setName);
        Optional.ofNullable(userAtualizado.getPassword()).ifPresent(
                senha -> user.setPassword(passwordEncoder.encode(senha))
        );
        Optional.ofNullable(userAtualizado.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(userAtualizado.getType()).ifPresent(user::setType);
        return usuarioRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user){ usuarioRepository.delete(user);}

    @Transactional
    public void deleteUserById(Long id){ usuarioRepository.deleteById(id);}

    @Transactional
    public void deleteUserByEmail(String email){
        User user = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("E-mail não encontrado")
        );
        usuarioRepository.deleteById(user.getId());
    }

    @Transactional
    public void deleteAllUsers(){ usuarioRepository.deleteAll();}
}
