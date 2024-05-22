package com.mycom.springboot.user.service;

import com.mycom.springboot.user.dto.UserResultDto;

public interface FriendService {
	UserResultDto friendList(int userSeq);
	UserResultDto searchUser(int userSeq, String str);
	UserResultDto getRequest(int userSeq);
	UserResultDto getRejected(int userSeq);
	UserResultDto deleteFriend(int userSeq, int friendSeq);
	UserResultDto addRequest(int userSeq, int friendSeq);
	UserResultDto updateRequest(int userSeq, int friendSeq, int mode);
}
