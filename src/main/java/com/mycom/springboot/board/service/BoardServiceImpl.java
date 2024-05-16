package com.mycom.springboot.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mycom.springboot.board.dao.BoardDao;
import com.mycom.springboot.board.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardDao boardDao;
	
	@Override
	public int boardInsert(BoardDto dto) {
	    return boardDao.boardInsert(dto);
	}

	@Override
	public int boardUpdate(BoardDto dto) {
	    return boardDao.boardUpdate(dto);
	}

	@Override
	public int boardDelete(int boardId) {
	    return boardDao.boardDelete(boardId);
	}

	@Override
	public BoardDto boardDetail(int boardId, int userSeq) { // 현재 세션 사용자의 userSeq
	    return boardDao.boardDetail(boardId);
	}

	@Override
	public List<BoardDto> boardList(int limit, int offset, String searchWord) {
		Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        params.put("searchWord", searchWord);

        return boardDao.boardList(params);
	}


	@Override
	public int boardListTotalCnt() {
	    return boardDao.boardListTotalCnt();
	}


	@Override
	public int boardListSearchWordTotalCnt(String searchWord) {
	    return boardDao.boardListSearchWordTotalCnt(searchWord);
	}
}