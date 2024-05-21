package com.mycom.springboot.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.auth.service.LoginService;
import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserResultDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService service;
	
	@Value("${app.userProfileImagePath}")
    String userProgileImagePath;
    
    @PostMapping(value="/login")
    public UserResultDto login(@RequestBody UserDto dto, HttpSession session){
        
        UserDto loginDto = service.login(dto);
        UserResultDto userResultDto = new UserResultDto();
        if( loginDto != null ) {
        	loginDto.setUserProfileImage(service.getImg(loginDto.getUserSeq()));
            session.setAttribute("userDto", loginDto);
            System.out.println(loginDto);
            
            
            userResultDto.setUserDto(loginDto);
            userResultDto.setResult("success");
            
            return userResultDto;
        }
        userResultDto.setResult("fail");
        return userResultDto;
    }
    
    @GetMapping(value="/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session){
        
        UserDto loginDto = (UserDto) session.getAttribute("userDto");
        Map<String, String> map = new HashMap<>();
        System.out.println(loginDto);
        if( loginDto != null ) {
            session.removeAttribute("userDto");
            
            map.put("result", "success");
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
        }
        map.put("result", "fail");
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
    }
}
