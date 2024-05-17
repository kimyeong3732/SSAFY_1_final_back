package com.mycom.springboot.user.service;

import org.springframework.stereotype.Service;

import com.mycom.springboot.user.dao.UserDao;
import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserResultDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
private final UserDao userDao;
	
	@Override
	public UserResultDto userRegister(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if(userDao.userRegister(userDto) == 1) {
			userResultDto.setResult("success");
		}
		else {
			userResultDto.setResult("fail");
		}
		return userResultDto;
	}
}
