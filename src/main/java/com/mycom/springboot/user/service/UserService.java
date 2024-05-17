package com.mycom.springboot.user.service;

import com.mycom.springboot.user.dto.UserResultDto;
import com.mycom.springboot.user.dto.UserDto;

public interface UserService {
	public UserResultDto userRegister(UserDto userDto);
}
