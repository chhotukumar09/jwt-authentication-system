package com.project.Basic.Login.System.DTO;

import com.project.Basic.Login.System.EnumValue.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private Role role;
}
