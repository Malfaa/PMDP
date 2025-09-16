package com.malfaa.pmdp.service;

import com.malfaa.pmdp.dto.UserCreateDTO;
import com.malfaa.pmdp.dto.UserResponseDTO;
import com.malfaa.pmdp.model.User;
import com.malfaa.pmdp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //RETORNA DA MÁQUINA A ZONA PARA MEDIR CORRETAMENTE O FUSO HORÁRIO
    @Transactional(readOnly = true)
    public UserResponseDTO searchById(Long id){ 
        User user = usuarioRepository.findById(id).orElseThrow(() -> new IllegalStateException("Usuário não encontrado."));
        return convertToResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO searchByEmail(String email){ 
        User user = usuarioRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("Usuário não encontrado."));
        return convertToResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> searchAll(){ 
        List<User> user = usuarioRepository.findAll();
        return user.stream().map( this::convertToResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<User> searchByFilteredUsers(List<Long> ids){ return usuarioRepository.findAllById(ids);}

    @Transactional
    public UserCreateDTO createUser(User novoUser){
        Optional<User> userExist = usuarioRepository.findByEmail(novoUser.getEmail());
        if (userExist.isPresent()){
            throw new IllegalArgumentException("Usuário com este e-mail já existe!");
        }
        String senhaCriptografada = passwordEncoder.encode(novoUser.getPassword());
        novoUser.setPassword(senhaCriptografada);

        User userSaved = usuarioRepository.save(novoUser);
        return convertToCreateDTO(userSaved);
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

    private UserCreateDTO convertToCreateDTO(User user){
        return new UserCreateDTO(user.getName(), user.getEmail(), user.getPassword(), user.getCpf(), user.getBirthday(), user.getType());
    };

    private UserResponseDTO convertToResponseDTO(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getType());
    };
    
}
