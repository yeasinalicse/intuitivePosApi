package com.intuitive.intuitivepos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    @Schema(example = "1234", description = "Password for login")
    private String password;
}