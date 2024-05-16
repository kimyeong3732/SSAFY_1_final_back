package com.mycom.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycom.springboot.auth.dao.LoginDao;
import com.mycom.springboot.auth.service.LoginService;
import com.mycom.springboot.board.dao.BoardDao;
import com.mycom.springboot.board.dto.BoardDto;
import com.mycom.springboot.board.service.BoardService;
import com.mycom.springboot.user.dao.UserDao;
import com.mycom.springboot.user.service.UserService;

@SpringBootTest
class JhchSpringBootProjectApplicationTests {

	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LoginDao loginDao;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardDao boardDao;
	
//	@Test
//	@DisplayName("testDI")
//	void testDI() {
//		assertNotNull(userDao, "userDao 다시 만들어");
//		assertNotNull(userService, "userService 다시 만들어");
//		assertNotNull(loginService, "loginService 다시 만들어");
//		assertNotNull(loginDao, "loginDao 다시 만들어");
//		assertNotNull(boardService, "boardService 다시 만들어");
//		assertNotNull(boardDao, "boardDao 다시 만들어");		
//	}
//	
//	@Test
//	@Transactional
//	@DisplayName("test Register")
//	void testRegister() {
//		UserDto dto = new UserDto();
//		dto.setUserEmail("qwqweqwe");
//		dto.setUserName("sdoifjsdo");
//		dto.setUserPassword("dfohs");
//		
//		assertEquals(1, userService.userRegister(dto));
//	}
	
//	@Test
//	@Transactional
//	@DisplayName("test Update")
//	void testUpdate() {
//		UserDto dto = new UserDto();
//		dto.setUserSeq(24);
//		dto.setUserName("qweqwe");
//		dto.setUserPassword("wqn eq ns");
//		
//		assertEquals(1, userService.userUpdate(dto));
//	}
//
//	@Test
//	@Transactional
//	@DisplayName("test Drop")
//	void testDrop() {
//		assertEquals(1, userService.userDrop(24));
//	}
//	
//	@Test
//	@DisplayName("test Login")
//	void testLogin() {
//		LoginDto dto = new LoginDto();
//		dto.setUserEmail("213124@123123.com");
//		dto.setUserPassword("1q2w3e4r!@");
//		
//		LoginDto res = loginService.login(dto);
//		assertNotNull(res, "loginDto 다시 만들어");
//		assertEquals(res.getUserEmail(), "213124@123123.com");
//		assertEquals(res.getUserName(), "김종훈213");
//	}
//	
//	@Test
//	@DisplayName("test boardInsert")
//	void testBoardInsert() {
//		BoardDto dto = new BoardDto();
//		dto.setUserSeq(22);
//		dto.setTitle("안녕");
//		dto.setContent("바보");
//		
//		assertEquals(1, boardService.boardInsert(dto));
//	}
//	
//	@Test
//	@DisplayName("test boardUpdate")
//	void testBoardUpdate() {
//		BoardDto dto = new BoardDto();
//		dto.setBoardId(592);
//		dto.setTitle("안안녕");
//		dto.setContent("바보몽충이");
//		
//		assertEquals(1, boardService.boardUpdate(dto));
//	}
//	
//	@Test
//	@DisplayName("test boardDelete")
//	void testBoardDelete() {
//		
//		assertEquals(1, boardService.boardDelete(592));
//	}
}
