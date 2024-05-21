package com.mycom.springboot.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.springboot.auth.dao.LoginDao;
import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserFileDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService{
    
	@Autowired
    LoginDao loginDao;
    
    @Override
    public UserDto login(UserDto dto) {
        
    	UserDto userDto = loginDao.login(dto.getUserEmail());
        
        if( userDto != null && userDto.getUserPassword().equals(dto.getUserPassword())) {
            // password null setting
            userDto.setUserPassword(null);
            return userDto;
        }else {
            return null;
        }
    }

	@Override
	public UserFileDto getImg(int userSeq) {
		UserFileDto userDto = loginDao.getImg(userSeq);
		
		if(userDto!=null && userDto.getFileName()!=null) {
			return userDto;
		}
		
		return null;
	}
}
