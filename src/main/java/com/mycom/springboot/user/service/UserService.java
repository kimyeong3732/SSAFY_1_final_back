package com.mycom.springboot.user.service;

import com.mycom.springboot.user.dto.UserResultDto;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserFileDto;

public interface UserService {
	public UserResultDto userRegister(UserDto userDto);
	public UserResultDto updateImg(UserFileDto userDto, MultipartHttpServletRequest request);
	public UserResultDto userUpdate(UserDto userDto);
	public UserResultDto userDelete(String userEmail);
}
