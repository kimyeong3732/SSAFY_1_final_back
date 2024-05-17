package com.mycom.springboot.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.user.dto.UserDto;

@Mapper
public interface LoginDao {
	UserDto login(String userEmail);
}
