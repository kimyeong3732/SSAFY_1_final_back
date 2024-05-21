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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.springboot.board.dto.BoardDto;
import com.mycom.springboot.board.dto.BoardResultDto;
import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserFileDto;
import com.mycom.springboot.user.dto.UserResultDto;
import com.mycom.springboot.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FriendController {
	
private final UserService userService;
	
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
	
	public ResponseEntity<Map<String, String>> updateUser(@RequestBody UserDto dto, HttpSession session) {
		UserResultDto userResultDto = userService.userUpdate(dto);
		Map<String, String> map = new HashMap<>();
		if("success".equals(userResultDto.getResult())) {
			map.put("result", "success");
			session.setAttribute("userDto", dto);
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		}
		else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    public UserResultDto updateImg(
            MultipartHttpServletRequest request) {
        
		UserFileDto userDto = new UserFileDto();
		userDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
		UserResultDto userResultDto = userService.updateImg(userDto, request);
        
        return userResultDto;     
    }
	
	public UserResultDto UserDelete(@PathVariable("userEmail") String userEmail) {
		UserResultDto userResultDto = userService.userDelete(userEmail);
		
		return userResultDto;
	}
}
