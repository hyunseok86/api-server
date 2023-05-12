package com.api.controller;

import com.api.dto.TokenDTO;
import com.api.security.JwtFilter;
import com.api.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.User;

import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("authenticate")
    public ResponseEntity authorize( @RequestBody User user) {

        TokenDTO tokenDto = authService.login(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getToken());
        return new ResponseEntity<>(tokenDto.getToken(), httpHeaders, HttpStatus.OK);
    }
}