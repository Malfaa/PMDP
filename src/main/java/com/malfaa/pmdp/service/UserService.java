package com.malfaa.pmdp.service;

import com.malfaa.pmdp.dto.userDto.UserCreateDTO;
import com.malfaa.pmdp.dto.userDto.UserResponseDTO;
import com.malfaa.pmdp.mapper.UserMapper;
import com.malfaa.pmdp.model.User;
import com.malfaa.pmdp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository usuarioRepository, PasswordEncoder passwordEncoder, UserMapper uMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = uMapper;
    }

    //RETORNA DA MÁQUINA A ZONA PARA MEDIR CORRETAMENTE O FUSO HORÁRIO
    @Transactional(readOnly = true)
    public UserResponseDTO searchById(Long id){ 
        User user = usuarioRepository.findById(id).orElseThrow(() -> new IllegalStateException("Usuário não encontrado."));
        return userMapper.responseToDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO searchByEmail(String email){ 
        User user = usuarioRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("Usuário não encontrado."));
        return userMapper.responseToDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> searchAll(){
        return userMapper.listToResponseDto(usuarioRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> searchByFilteredUsers(List<Long> ids){
        return userMapper.listToResponseDto(usuarioRepository.findAllById(ids));
    }

    @Transactional
    public UserCreateDTO createUser(User novoUser){
        Optional<User> userExist = usuarioRepository.findByEmail(novoUser.getEmail());
        if (userExist.isPresent()){
            throw new IllegalArgumentException("Usuário com este e-mail já existe!");
        }
        String senhaCriptografada = passwordEncoder.encode(novoUser.getPassword());
        novoUser.setPassword(senhaCriptografada);

        User userSaved = usuarioRepository.save(novoUser);
        return userMapper.createToDto(userSaved);
    }

    @Transactional
    public UserResponseDTO editUser(Long usuarioAntigo, User userAtualizado){
        User user = usuarioRepository.findById(usuarioAntigo).orElseThrow(
                () -> new RuntimeException("Usuario do ID: "+ usuarioAntigo + " não encontrado")
        );

        Optional.ofNullable(userAtualizado.getName()).ifPresent(user::setName);
        Optional.ofNullable(userAtualizado.getPassword()).ifPresent(
                senha -> user.setPassword(passwordEncoder.encode(senha))
        );
        Optional.ofNullable(userAtualizado.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(userAtualizado.getType()).ifPresent(user::setType);
        return userMapper.responseToDto(usuarioRepository.save(user));
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
