package com.mycom.springboot.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.auth.service.LoginService;
import com.mycom.springboot.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService service;
	
	@Value("${app.userProfileImagePath}")
    String userProgileImagePath;
    
    @PostMapping(value="/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto dto, HttpSession session){
        
        UserDto loginDto = service.login(dto);
        Map<String, String> map = new HashMap<>();
        System.out.println(loginDto);
        if( loginDto != null ) {
            session.setAttribute("userDto", loginDto);
            
            map.put("result", "success");
            map.put("userName", loginDto.getUserName());
            map.put("userProfileImage", userProgileImagePath + loginDto.getUserProfileImage());
            map.put("userRole", loginDto.getUserClsf());
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
        }
        map.put("result", "fail");
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
    }
}
