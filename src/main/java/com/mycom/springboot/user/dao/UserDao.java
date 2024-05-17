package com.mycom.springboot.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.user.dto.UserDto;

@Mapper
public interface UserDao {
	int userRegister(UserDto userDto);
}
