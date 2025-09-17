package com.malfaa.pmdp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malfaa.pmdp.dto.UserCreateDTO;
import com.malfaa.pmdp.dto.UserResponseDTO;
import com.malfaa.pmdp.model.User;
import com.malfaa.pmdp.model.enums.Perfil;
import com.malfaa.pmdp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks()
    private UserService userService;

    @Test
    void verificaSeHaUserERetorna(){
        UserResponseDTO responseDTO = new UserResponseDTO(
                null, "Nome Genérico", "email@gmail.com", Perfil.Administrador
        );

        User userExistente = new User();
        userExistente.setId(1L);
        userExistente.setName(responseDTO.name());
        userExistente.setEmail(responseDTO.email());
        userExistente.setType(responseDTO.type());

        when(userRepository.findByEmail("email@gmail.com")).thenReturn(Optional.of(userExistente));

        UserResponseDTO result = userService.searchByEmail(userExistente.getEmail());

        assertNotNull(result);
        assertEquals("Nome Genérico", result.name());
        assertEquals("email@gmail.com", result.email());

        verify(userRepository, times(1)).findByEmail("email@gmail.com");
    }

    @Test
    void deveVerificarSeHaRetornoDeListaUsers(){
        UserResponseDTO userInput = new UserResponseDTO(null, "Teste", "Teste@gmail.com", Perfil.Mentor);
        UserResponseDTO userInput2 = new UserResponseDTO(null, "Teste2", "Teste2@gmail.com", Perfil.Mentorado);

        User user1 = new User();
        user1.setId(1L);
        user1.setName(userInput.name());
        user1.setEmail(userInput.email());
        user1.setType(userInput.type());
        
        User user2 = new User();
        user2.setId(2L);
        user2.setName(userInput2.name());
        user2.setEmail(userInput2.email());
        user2.setType(userInput2.type());
        

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserResponseDTO> result = userService.searchAll();
        
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch( u -> u.id().equals(1L) && u.name().equals("Teste")));
        assertTrue(result.stream().anyMatch( u -> u.id().equals(2L) && u.name().equals("Teste2")));

        verify(userRepository, times(1)).findAll();
    }
    
    @Test
    void deveCriarNovoUsuarioVerificandoSeJaNaoExisteAnteriormente(){
        UserCreateDTO inputDTO = new UserCreateDTO(
            "Joel", "joel@gmail.com", "senha123","111.222.333-11.", LocalDate.of(2000,10,1), Perfil.Mentorado
            );

        User salvarUser = new User();
        salvarUser.setName(inputDTO.name());
        salvarUser.setEmail(inputDTO.email());
        salvarUser.setCpf(inputDTO.cpf());
        salvarUser.setBirthday(inputDTO.birthday());
        salvarUser.setPassword(inputDTO.password());
        salvarUser.setType(inputDTO.type());
        
        when(userRepository.findByEmail("joel@gmail.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("senha123");
        when(userRepository.save(any(User.class))).thenReturn(salvarUser);

        UserCreateDTO result = userService.createUser(salvarUser);

        assertNotNull(result);
        assertEquals("joel@gmail.com", result.email());
        assertEquals("senha123", result.password());

        verify(userRepository, times(1)).save(any(User.class));
    }
}
