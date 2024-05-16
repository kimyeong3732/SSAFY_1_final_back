package com.mycom.springboot.user.service;

import org.springframework.stereotype.Service;

import com.mycom.springboot.user.dao.UserDao;
import com.mycom.springboot.user.dto.UserDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;

	@Override
	public int userRegister(UserDto userDto) {
		return userDao.userRegister(userDto);
	}
	
	public int userUpdate(UserDto userDto) {
		return userDao.userUpdate(userDto);
	}

	@Override
	public int userDrop(int userSeq) {
		return userDao.userDrop(userSeq);
	}

}
