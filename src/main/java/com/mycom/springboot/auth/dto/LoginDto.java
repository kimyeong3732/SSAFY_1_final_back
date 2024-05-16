package com.mycom.springboot.auth.dto;

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

// UserDto 와 분리
public class LoginDto {
    private int userSeq;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userProfileImageUrl;
}
