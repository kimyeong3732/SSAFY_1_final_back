package com.mycom.springboot.auth.service;

import com.mycom.springboot.auth.dto.LoginDto;

public interface LoginService {
	LoginDto login(LoginDto dto);
}
