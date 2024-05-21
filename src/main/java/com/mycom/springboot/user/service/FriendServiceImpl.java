package com.mycom.springboot.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.mycom.springboot.user.dao.UserDao;
import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserFileDto;
import com.mycom.springboot.user.dto.UserResultDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
	
	private final UserDao userDao;

	@Override
	public UserResultDto friendList(int userSeq) {
		UserResultDto userResultDto = new UserResultDto();
		
		List<Integer> friends = userDao.getFriends(userSeq);
		
		if(friends != null ) {
			List<UserDto> list = new ArrayList<>();
			
			for(int friend: friends) {
				UserDto user = userDao.getUser(friend);
				UserFileDto file = new UserFileDto();
				
				file.setFileUUID(userDao.getImg(friend));
				
				user.setUserProfileImage(file);
				
				list.add(user);
			}
			
			userResultDto.setList(list);
			userResultDto.setResult("success");
			
			return userResultDto;
		}
		
		userResultDto.setResult("fail");
		
		return userResultDto;
	}

	@Override
	public UserResultDto getRequest(int userSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResultDto getRejected(int userSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResultDto deleteFriend(int userSeq, int friendSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResultDto addRequest(int userSeq, int friendSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResultDto updateRequest(int userSeq, int friendSeq, int mode) {
		// TODO Auto-generated method stub
		return null;
	}
}