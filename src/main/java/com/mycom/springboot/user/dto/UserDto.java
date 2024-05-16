package com.mycom.springboot.user.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class UserDto {
    private int userSeq;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userProfileImageUrl;
    private Date userRegisterDate;
   
}
