package com.mycom.springboot.auth.service;

import org.springframework.stereotype.Service;

import com.mycom.springboot.auth.dao.LoginDao;
import com.mycom.springboot.auth.dto.LoginDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService{
    
	private final LoginDao loginDao;
	
    @Override
    public LoginDto login(LoginDto dto) {
        
        LoginDto userDto = loginDao.login(dto.getUserEmail());
        
        if( userDto != null && userDto.getUserPassword().equals(dto.getUserPassword())) {
            // password null setting
            userDto.setUserPassword(null);
            return userDto;
        }else {
            return null;
        }
    }
}
