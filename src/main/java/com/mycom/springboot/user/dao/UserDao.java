package com.mycom.springboot.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	List<Integer> getFriends(int userSeq);
	List<UserDto> searchUser(
	        @Param("userSeq") int userSeq, 
	        @Param("str") String str
	        );
	UserDto getUser(int userSeq);
	List<Integer> getRequest(int userSeq);
	List<Integer> getNotRejected(int userSeq);
	List<Integer> getRejected(int userSeq);
	int deleteRequest(
	        @Param("userSeq") int userSeq, 
	        @Param("friendSeq") int friendSeq
	        );
	int addRequest(
	        @Param("userSeq") int userSeq, 
	        @Param("friendSeq") int friendSeq
	        );
	List<Integer> getRequestUseFriend(
	        @Param("userSeq") int userSeq, 
	        @Param("friendSeq") int friendSeq
	        );
	int updateRequest(
	        @Param("userSeq") int userSeq, 
	        @Param("friendSeq") int friendSeq,
	        @Param("mode") String mode
	        );
}
