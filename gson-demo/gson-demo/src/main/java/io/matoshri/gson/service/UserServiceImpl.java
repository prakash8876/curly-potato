package io.matoshri.gson.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.matoshri.gson.dto.UserDTO;
import io.matoshri.gson.entity.User;
import io.matoshri.gson.exception.UserNotFoundException;
import io.matoshri.gson.repo.UserRepository;
import io.matoshri.gson.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value(value = "${jsonFilePath}")
    private String jsonFilePath;

    @Override
    public List<UserDTO> saveFromJson() {
        List<UserDTO> usersFromJson = getUsersFromJson();
        List<User> users = userRepository.saveAll(usersFromJson.stream().map(this::convertToUser).collect(Collectors.toList()));
        log.info(Constants.SAVED_USER + users.size());
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (Objects.isNull(user)) {
            log.error(Constants.USER_NOT_FOUND + " " + email);
            throw new UserNotFoundException(Constants.USER_NOT_FOUND + " " + email);
        }
        return convertToDto(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            log.error(Constants.USER_NOT_FOUND + " " + username);
            throw new UserNotFoundException(Constants.USER_NOT_FOUND + " " + username);
        }
        return convertToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsersByAge(int age) {
        List<User> byAge = userRepository.findAllByAge(age);
        if (byAge.isEmpty()) {
            log.info(Constants.NO_USERS_FOUND);
            return Collections.emptyList();
        }
        return byAge.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.info(Constants.NO_USERS_FOUND);
            return Collections.emptyList();
        }
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User save = userRepository.save(convertToUser(userDTO));
        log.info(Constants.SAVED_USER + userDTO.getName());
        return convertToDto(save);
    }

    @Transactional
    @Override
    public List<UserDTO> saveUsers(List<UserDTO> userDTOS) {
        List<User> users = userRepository.saveAll(userDTOS.stream().map(this::convertToUser).collect(Collectors.toList()));
        log.info(Constants.SAVED_USER + users.size());
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    private List<UserDTO> getUsersFromJson() {
        List<UserDTO> userDTOS = new LinkedList<>();
        try {
            if (StringUtils.isEmpty(jsonFilePath)) {
                log.error("JSON File Path is empty");
            }
            log.info("Parsing JSON from : {}", jsonFilePath);
            userDTOS = new Gson().fromJson(new FileReader(jsonFilePath), new TypeToken<List<UserDTO>>() {
            }.getType());
            log.info("Parsed records: {}", userDTOS.size());
        } catch (FileNotFoundException e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            throw new RuntimeException("Can't read file");
        }
        return userDTOS;
    }
}
