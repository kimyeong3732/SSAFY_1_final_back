package com.mycom.springboot.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserFileDto;

@Mapper
public interface LoginDao {
	UserDto login(String userEmail);
	UserFileDto getImg(int userSeq);
}
