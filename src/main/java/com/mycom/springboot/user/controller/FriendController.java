package com.mycom.springboot.user.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserResultDto;
import com.mycom.springboot.user.service.FriendService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FriendController {
	
	private final FriendService friendService;
	
	@GetMapping("/friends")
	public UserResultDto friendList(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		UserResultDto userResultDto = friendService.friendList(userDto.getUserSeq());
		
		return userResultDto;
	}
	
	@GetMapping("/friends/request")
	public UserResultDto getRequest(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		UserResultDto userResultDto = friendService.getRequest(userDto.getUserSeq());
		
		return userResultDto;
	}
	
	@GetMapping("/friends/reject")
	public UserResultDto getRejected(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		UserResultDto userResultDto = friendService.getRejected(userDto.getUserSeq());
		
		return userResultDto;
	}
	
	@DeleteMapping("/friends/{friendSeq}")
	public UserResultDto deleteFriend(@PathVariable("friendSeq") int friendSeq, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		UserResultDto userResultDto = friendService.deleteFriend(userDto.getUserSeq(), friendSeq);
		
		return userResultDto;
	}
	
	@PostMapping("/friends")
	public UserResultDto addRequest(@RequestBody HashMap<String, Integer> data, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		UserResultDto userResultDto = friendService.addRequest(userDto.getUserSeq(), data.get("friendSeq"));
		
		return userResultDto;
	}
	
	@PutMapping("/friends/{friendSeq}")
	public UserResultDto updateRequest(@PathVariable("friendSeq") int friendSeq, @RequestBody HashMap<String, Integer> data, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		UserResultDto userResultDto = friendService.updateRequest(userDto.getUserSeq(), friendSeq, data.get("mode"));
		
		return userResultDto;
	}
}
