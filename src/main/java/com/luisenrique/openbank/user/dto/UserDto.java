package com.luisenrique.openbank.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String dni;
    private String name;
    private String email;
    private String role;
}

