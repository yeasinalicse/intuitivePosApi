package com.intuitive.intuitivepos.login.service;

import com.intuitive.intuitivepos.dto.LoginResponse;
import com.intuitive.intuitivepos.login.repository.AuthRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LoginResponse login(String password) {
        return authRepository.callLoginProc(password);
    }
}
