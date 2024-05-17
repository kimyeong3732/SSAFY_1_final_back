package com.mycom.springboot.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserResultDto;
import com.mycom.springboot.user.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
private final UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<Map<String, String>> register(@RequestBody UserDto dto) {
		UserResultDto userResultDto = userService.userRegister(dto);
		Map<String, String> map = new HashMap<>();
		if("success".equals(userResultDto.getResult())) {
			map.put("result", "success");
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		}
		else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
