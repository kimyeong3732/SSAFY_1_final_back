package com.mycom.springboot.board.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.auth.dto.LoginDto;
import com.mycom.springboot.board.dto.BoardDto;
import com.mycom.springboot.board.dto.BoardResponse;
import com.mycom.springboot.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/board/list")
	public List<BoardDto> boardList(
			@RequestParam("limit") int size,
			@RequestParam("offset") int offset,
			@RequestParam(name = "searchWord", required = false) String searchWord
			) {
		
        List<BoardDto> boardList = boardService.boardList(size, offset, searchWord);
        
        if (!boardList.isEmpty()) {
            return boardList;
        } else {
            return null;
        }
	}
	
	@GetMapping("/board/{boardId}")
	public ResponseEntity<Map<String, String>> boardDetail(@PathVariable("boardId") int boardId, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		LoginDto loginDto = (LoginDto) session.getAttribute("loginDto");
		
		Map<String, String> map = new HashMap<>();
		
		if (loginDto == null) {
			map.put("result", "login");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
		}
		
		BoardDto boardDto = boardService.boardDetail(boardId, loginDto.getUserSeq());
		System.out.println("게시판 상세");
		
		map.put("result", "success");
		map.put("boardId", Integer.toString(boardDto.getBoardId()));
		map.put("userSeq", Integer.toString(boardDto.getUserSeq()));
		map.put("userName", loginDto.getUserName());
		map.put("title", boardDto.getTitle());
		map.put("content", boardDto.getContent());
		map.put("regDt", boardDto.getRegDt().toString());
		
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

	@GetMapping("/board/boardListTotalCnt")
	public int boardListTotalCnt() {
		int totalCnt = boardService.boardListTotalCnt();
        return totalCnt;
	}

	@PostMapping("/board/list")
	public ResponseEntity<Map<String, String>> boardInsert(BoardDto boardDto) {
		System.out.println("게시판 추가");
		Map<String, String> map = new HashMap<>();

		LocalDateTime now = LocalDateTime.now();
        LocalDateTime date = LocalDateTime.from(now.atZone(ZoneId.systemDefault()).toInstant());
        boardDto.setRegDt(date);
        
		int res = boardService.boardInsert(boardDto);

		if (res == 1) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}

		map.put("result", "fail");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/board/{boardId}")
	public ResponseEntity<Map<String, String>> boardDelete(@PathVariable("boardId") int boardId) {
		System.out.println("게시판 삭제");
		Map<String, String> map = new HashMap<>();

		int res = boardService.boardDelete(boardId);

		if (res == 1) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}

		map.put("result", "fail");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/board/{boardId}")
	public ResponseEntity<Map<String, String>> boardUpdate(@PathVariable("boardId") int boardId, BoardDto boardDto) {
		System.out.println("게시판 수정");
		Map<String, String> map = new HashMap<>();

		int res = boardService.boardUpdate(boardDto);

		if (res == 1) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}

		map.put("result", "fail");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}
