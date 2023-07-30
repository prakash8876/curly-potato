package io.matoshri.gson.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {
    private String name;
    @Email(message = "Enter proper email address")
    private String email;
    @Min(20)
    @Max(60)
    private int age;
    private String username;
    private String password;
}
