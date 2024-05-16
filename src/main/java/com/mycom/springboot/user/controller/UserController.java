package com.mycom.springboot.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private final UserService userService;

	// 유저 등록
	@PostMapping("/users")
	public ResponseEntity<Map<String, String>> userRegister(UserDto dto) {
		int res = userService.userRegister(dto);
		
		Map<String, String> map = new HashMap<>();
		
		// 등록 성공
		if (res == 1) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		map.put("result", "fail");
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 유저 수정
	@PutMapping("/users/{userSeq}")
	public int userUpdate(@PathVariable("userSeq") int userSeq, @RequestBody UserDto dto) {
		return userService.userUpdate(dto);
	}
	
	// 유저 삭제
	@DeleteMapping("/users/{userSeq}")
	public int userDrop(@PathVariable("userSeq") int userSeq) {
		return userService.userDrop(userSeq);
	}
}
