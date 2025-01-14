package me.kangjunyi.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;
}
/*
    AddUserRequest 객체를 argument
 */
