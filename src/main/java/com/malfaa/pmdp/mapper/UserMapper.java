package com.malfaa.pmdp.mapper;

import com.malfaa.pmdp.dto.userDto.UserCreateDTO;
import com.malfaa.pmdp.dto.userDto.UserResponseDTO;
import com.malfaa.pmdp.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserCreateDTO createToDto(User user){
        if (user == null){return null;}
        return new UserCreateDTO(
                user.getName(), user.getEmail(), user.getPassword(), user.getCpf(), user.getBirthday(), user.getType()
        );
    }
    public UserResponseDTO responseToDto(User user){
        if (user == null){return null;}
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getType());
    }

    public List<UserResponseDTO> listToResponseDto(List<User> users){
        if (users == null){return null;}
        return users.stream().map(this::responseToDto).collect(Collectors.toList());
    }

    public List<UserCreateDTO> listToCreateDto(List<User> users){
        if (users == null){return null;}
        return users.stream().map(this::createToDto).collect(Collectors.toList());
    }
}
