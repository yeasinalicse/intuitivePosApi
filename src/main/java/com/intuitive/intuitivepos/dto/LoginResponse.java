package com.intuitive.intuitivepos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginResponse {
    @Schema(example = "true")  private boolean success;
    @Schema(example = "Login successful") private String message;
    @Schema(description = "Dynamic data that can be either a JSON object or array")
    private Object data;
}

