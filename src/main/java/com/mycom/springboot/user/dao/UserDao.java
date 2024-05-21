package com.mycom.springboot.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserFileDto;

@Mapper
public interface UserDao {
	int userRegister(UserDto userDto);
	int getSeq(UserDto userDto);
	int setImg(int userSeq);
	int userUpdate(UserDto userDto);
	int userDelete(int userSeq);
	int updateImg(UserFileDto userDto);
}
