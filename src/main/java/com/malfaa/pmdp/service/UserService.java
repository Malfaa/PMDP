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
    public UserResponseDTO createUser(UserCreateDTO createDTO){
        Optional<User> userExist = usuarioRepository.findByEmail(createDTO.email());
        if (userExist.isPresent()){
            throw new IllegalArgumentException("Usuário com este e-mail já existe!");
        }
        User newUser = userMapper.createDtoToEntity(createDTO);

        String senhaCriptografada = passwordEncoder.encode(createDTO.password());
        newUser.setPassword(senhaCriptografada);
        User userSaved = usuarioRepository.save(newUser);
        return userMapper.responseToDto(userSaved);
    }

    @Transactional
    public UserResponseDTO editUser(Long usuarioAntigo, UserCreateDTO createDTOUpdated){
        User user = usuarioRepository.findById(usuarioAntigo).orElseThrow(
                () -> new RuntimeException("Usuario do ID: "+ usuarioAntigo + " não encontrado")
        );

        Optional.ofNullable(createDTOUpdated.name()).ifPresent(user::setName);
        Optional.ofNullable(createDTOUpdated.password()).ifPresent(
                senha -> user.setPassword(passwordEncoder.encode(senha))
        );
        Optional.ofNullable(createDTOUpdated.email()).ifPresent(user::setEmail);

        User userEdited = usuarioRepository.save(user);

        return userMapper.responseToDto(userEdited);
    }

    @Transactional
    public void deleteUser(UserResponseDTO responseDTO){ 
        User userSearch = usuarioRepository.findById(responseDTO.id()).orElseThrow(
            () -> new RuntimeException("Usuário não encontrado")
        );
        usuarioRepository.delete(userSearch);
    }

   @Transactional
    public void deleteUserById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id); 
    }

    @Transactional
    public void deleteUserByEmail(String email){
        User user = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("E-mail não encontrado")
        );
        usuarioRepository.deleteById(user.getId());
    }

    /*
    @Transactional
    public void deleteAllUsers(){ 
        usuarioRepository.deleteAll();
    }
    */
}
