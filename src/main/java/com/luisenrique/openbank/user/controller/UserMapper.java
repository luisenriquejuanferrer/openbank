package com.luisenrique.openbank.user.controller;

import com.luisenrique.openbank.user.dto.UserDto;
import com.luisenrique.openbank.user.model.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .dni(user.getDni())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
