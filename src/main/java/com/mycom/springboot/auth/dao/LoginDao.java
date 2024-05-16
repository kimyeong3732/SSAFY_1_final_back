package com.mycom.springboot.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.auth.dto.LoginDto;

@Mapper
public interface LoginDao {
	LoginDto login(String userEmail);
}
