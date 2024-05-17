package com.mycom.springboot.board.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.springboot.board.dto.BoardDto;
import com.mycom.springboot.board.dto.BoardParamDto;
import com.mycom.springboot.board.dto.BoardResultDto;

public interface BoardService {
	BoardResultDto boardList(BoardParamDto boardParamDto);
    BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);

    BoardResultDto boardDetail(BoardParamDto boardParamDto);

    BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);
    BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);
    BoardResultDto boardDelete(int boardId);
	
}
