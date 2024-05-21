package com.mycom.springboot.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserFileDto {
	private int fileId;
	private int userSeq;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private String fileUUID;
	private LocalDateTime regDt;
}
