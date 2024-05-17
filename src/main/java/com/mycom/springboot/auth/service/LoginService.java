package com.mycom.springboot.auth.service;

import com.mycom.springboot.user.dto.UserDto;

public interface LoginService {
	UserDto login(UserDto dto);
}
