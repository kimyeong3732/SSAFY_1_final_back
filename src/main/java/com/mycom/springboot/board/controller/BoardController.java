package com.mycom.springboot.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.springboot.board.dto.BoardDto;
import com.mycom.springboot.board.dto.BoardParamDto;
import com.mycom.springboot.board.dto.BoardResultDto;
import com.mycom.springboot.board.service.BoardService;
import com.mycom.springboot.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BoardController {

private final BoardService service;
	
    @GetMapping(value="/boards")
    public BoardResultDto boardList(BoardParamDto boardParamDto){
        
        BoardResultDto boardResultDto;

        if( boardParamDto.getSearchWord().isEmpty() ) {
            boardResultDto = service.boardList(boardParamDto);
        }else {
            boardResultDto = service.boardListSearchWord(boardParamDto);
        }
        
        return boardResultDto;
    }

    
    @GetMapping(value = "/boards/{boardId}")
    public ResponseEntity<BoardResultDto> boardDetail(@PathVariable("boardId") int boardId, HttpSession session) {
        try {
            UserDto userDto = (UserDto) session.getAttribute("userDto");

            // Check if userDto is null and handle the case
            if (userDto == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401 Unauthorized
            }

            BoardParamDto boardParamDto = new BoardParamDto();
            boardParamDto.setUserSeq(userDto.getUserSeq());
            boardParamDto.setBoardId(boardId);

            BoardResultDto boardResultDto = service.boardDetail(boardParamDto);

            // 게시글 작성자와 현 사용자가 동일한지 확인
            if (userDto.getUserSeq() == boardResultDto.getDto().getUserSeq()) {
                boardResultDto.getDto().setSameUser(true);
            }

            return new ResponseEntity<>(boardResultDto, HttpStatus.OK);
        } catch (Exception e) {
            // 로그를 남기거나 필요한 예외 처리를 수행합니다.
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
    
    @PostMapping(value="/boards")
    public BoardResultDto boardInsert(
            BoardDto boardDto, 
            MultipartHttpServletRequest request) {
        
        boardDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
        BoardResultDto boardResultDto = service.boardInsert(boardDto, request);
        
        return boardResultDto;     
    }
    
    // PUT + multipart/form-data (X)
    // In RESTful,
    // PUT & DELETE methods are defined to be idempotent
    
    @PostMapping(value="/boards/{boardId}") 
    public BoardResultDto boardUpdate(
            BoardDto boardDto, 
            MultipartHttpServletRequest request){
        boardDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
        BoardResultDto boardResultDto = service.boardUpdate(boardDto, request);

        return boardResultDto;        
    }
    
    @DeleteMapping(value="/boards/{boardId}") 
    public BoardResultDto boardDelete(@PathVariable(value="boardId") int boardId){
        BoardResultDto boardResultDto = service.boardDelete(boardId);
        
        return boardResultDto;         
    }

	

}
