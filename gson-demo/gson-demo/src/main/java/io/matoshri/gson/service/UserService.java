package io.matoshri.gson.service;

import io.matoshri.gson.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> saveFromJson();

    UserDTO getUserByEmail(String email);
    UserDTO getUserByUsername(String username);
    List<UserDTO> getAllUsersByAge(int age);

    List<UserDTO> getAllUsers();

    UserDTO saveUser(UserDTO userDTO);

    List<UserDTO> saveUsers(List<UserDTO> userDTOS);
}
