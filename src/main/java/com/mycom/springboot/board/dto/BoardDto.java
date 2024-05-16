package com.mycom.springboot.board.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

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
public class BoardDto {
    private int boardId;
    private int userSeq;
    private String userName;
    private String title;
    private String content;
    
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime regDt;
    private int readCount;
    
}
