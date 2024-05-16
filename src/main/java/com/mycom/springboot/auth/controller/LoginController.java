package com.mycom.springboot.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.auth.dto.LoginDto;
import com.mycom.springboot.auth.service.LoginService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	
    private final LoginService service;
    
    @PostMapping(value="/login")
    public ResponseEntity<Map<String, String>> login(LoginDto dto, HttpSession session){
        
        LoginDto loginDto = service.login(dto);
        Map<String, String> map = new HashMap<>();
        if( loginDto != null ) {
            session.setAttribute("loginDto", loginDto);
            map.put("result", "success");
            map.put("userSeq", Integer.toString(loginDto.getUserSeq()));
            map.put("userName", loginDto.getUserName());
            map.put("userEmail", loginDto.getUserEmail());
            map.put("userProfileImageUrl", loginDto.getUserProfileImageUrl());
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
        }
        map.put("result", "fail");
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
    }
}
