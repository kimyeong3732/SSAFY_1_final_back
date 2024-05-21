package com.mycom.springboot.user.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResultDto {
	private UserDto userDto;
	private List<UserDto> list;
	private String result; // success, fail 등
}
