package com.ililog.api.controller;

import com.ililog.api.config.AppConfig;
import com.ililog.api.request.Login;
import com.ililog.api.request.Signup;
import com.ililog.api.response.SessionResponse;
import com.ililog.api.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AppConfig appConfig;

    // Security Login 방식 사용에 따른 중지
//    @GetMapping("/auth/login")
//    public String login() {
//        return "로그인 페이지";
//    }

    @PostMapping("/auth/signup")
    public void signup(@RequestBody Signup signup) {
        authService.signup(signup);
    }


}
