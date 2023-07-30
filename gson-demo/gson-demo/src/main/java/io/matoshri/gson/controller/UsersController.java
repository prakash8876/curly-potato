package io.matoshri.gson.controller;

import io.matoshri.gson.dto.UserDTO;
import io.matoshri.gson.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
