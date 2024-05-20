package com.mycom.springboot.code.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.code.dto.CodeDto;
import com.mycom.springboot.code.service.CodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CodeController {

	private final CodeService codeService;
	
	@GetMapping("/codes")
	public List<CodeDto> codeList(@RequestParam("groupCode") String groupCode) {
		return codeService.codeList(groupCode);
	}
}
