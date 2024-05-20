package com.mycom.springboot.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.code.dto.CodeDto;

@Mapper
public interface CodeDao {
	List<CodeDto> codeList(String groupCode);
}
