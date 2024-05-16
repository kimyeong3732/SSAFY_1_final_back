package com.mycom.springboot.user.service;

import com.mycom.springboot.user.dto.UserDto;

public interface UserService {
	int userRegister(UserDto userDto);
	int userUpdate(UserDto userDto);
	int userDrop(int userSeq);
}
