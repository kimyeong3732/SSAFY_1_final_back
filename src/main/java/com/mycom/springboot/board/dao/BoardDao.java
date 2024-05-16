package com.mycom.springboot.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.board.dto.BoardDto;

@Mapper
public interface BoardDao {
	int boardInsert(BoardDto dto);
	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);

	BoardDto boardDetail(int boardId);

	List<BoardDto> boardList(Map<String, Object> params);
	int boardListTotalCnt();

	List<BoardDto> boardListSearchWord(int limit, int offset, String searchWord);
	int boardListSearchWordTotalCnt(String searchWord);
}
