package com.mycom.springboot.auth.service;

import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserFileDto;

public interface LoginService {
	UserDto login(UserDto dto);
	UserFileDto getImg(int userSeq);
}
