package com.mycom.springboot.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserFileDto;

@Mapper
public interface UserDao {
	int userRegister(UserDto userDto);
	int getSeq(UserDto userDto);
	int setImg(int userSeq);
	String getImg(int userSeq);
	int userUpdate(UserDto userDto);
	int userDelete(int userSeq);
	int updateImg(UserFileDto userDto);
	List<Integer> getUserBoard(int userSeq);
	int deleteBoard(int userSeq);
	int deleteBoardFile(int boardId);
	int deleteBoardUser(int userSeq);
	int deleteFavorite(int userSeq);
	int deletePrev(int userSeq);
	int deleteFriend(int userSeq);
	int deleteUserFile(int userSeq);
	int deleteUser(int userSeq);
}
