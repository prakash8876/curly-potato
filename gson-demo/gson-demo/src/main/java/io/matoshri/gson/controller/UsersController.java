package io.matoshri.gson.controller;

import io.matoshri.gson.dto.UserDTO;
import io.matoshri.gson.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PostMapping("from-json")
    public ResponseEntity<List<UserDTO>> saveUsersFromJson() {
        return ResponseEntity.ok(userService.saveFromJson());
    }

    @GetMapping("email")
    public UserDTO getUserByEmail(@RequestParam(name = "email", required = true) String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("username/{username}")
    public UserDTO getUserByUsername(@PathVariable(value = "username") String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("by-age/{age}")
    public List<UserDTO> getAllUsersByAge(@PathVariable(value = "age") int age) {
        return userService.getAllUsersByAge(age);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("save")
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PostMapping("save-all")
    public List<UserDTO> saveUsers(@RequestBody @Valid List<UserDTO> userDTOS) {
        return userService.saveUsers(userDTOS);
    }
}
