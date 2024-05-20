package com.mycom.springboot.code.service;

import java.util.List;

import com.mycom.springboot.code.dto.CodeDto;

public interface CodeService {
	List<CodeDto> codeList(String groupCode);
}
