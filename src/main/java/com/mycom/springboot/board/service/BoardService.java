package com.mycom.springboot.board.service;

import java.util.List;

import com.mycom.springboot.board.dto.BoardDto;

public interface BoardService {
	int boardInsert(BoardDto dto);
	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);

	BoardDto boardDetail(int boardId, int userSeq);

	List<BoardDto> boardList(int limit, int offset, String searchWord);
	int boardListTotalCnt();

	int boardListSearchWordTotalCnt(String searchWord);
	
}
