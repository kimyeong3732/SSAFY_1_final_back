package com.mycom.springboot.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.springboot.board.dao.BoardDao;
import com.mycom.springboot.board.dto.BoardDto;
import com.mycom.springboot.board.dto.BoardFileDto;
import com.mycom.springboot.board.dto.BoardParamDto;
import com.mycom.springboot.board.dto.BoardResultDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	@Value("${app.fileupload.upload.path}")
    String uploadPath;
	private final BoardDao dao;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

    @Override
    public BoardResultDto boardList(BoardParamDto boardParamDto) {
        
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {
            List<BoardDto> list = dao.boardList(boardParamDto);
            int count = dao.boardListTotalCount();            
            boardResultDto.setList(list);
            boardResultDto.setCount(count);
            boardResultDto.setResult(SUCCESS);
            
        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }

    @Override
    public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {
        
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {
            List<BoardDto> list = dao.boardListSearchWord(boardParamDto);
            int count = dao.boardListSearchWordTotalCount(boardParamDto);
            
            boardResultDto.setList(list);
            boardResultDto.setCount(count);
            
            boardResultDto.setResult(SUCCESS);
        
        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }

    @Override
    public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
        
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {
            int userReadCnt = dao.boardUserReadCount(boardParamDto);
            if( userReadCnt == 0 ) {
                dao.boardUserReadInsert(boardParamDto.getBoardId(), boardParamDto.getUserSeq());
                dao.boardReadCountUpdate(boardParamDto.getBoardId());
            }
            
            BoardDto dto = dao.boardDetail(boardParamDto);
            List<BoardFileDto> fileList = dao.boardDetailFileList(dto.getBoardId());

            dto.setFileList(fileList);
            boardResultDto.setDto(dto);
            
            boardResultDto.setResult(SUCCESS);
            
        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request) {
        
        BoardResultDto boardResultDto = new BoardResultDto();

        List<File> rollbackFileList = new ArrayList<>();
        
        try {
            dao.boardInsert(dto); // useGeneratedKeys="true" keyProperty="boardId"

            List<MultipartFile> fileList = request.getFiles("file");
    
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            
            for (MultipartFile part : fileList) {

                int boardId = dto.getBoardId();
                
                String fileName = part.getOriginalFilename();
                
                //Random File Id
                UUID uuid = UUID.randomUUID();
                
                //file extension
                String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
            
                String fileUUID = uuid + "." + extension;
            
                File saveFile = new File(uploadPath + File.separator + fileUUID);
                
                rollbackFileList.add(saveFile);
                
                part.transferTo(saveFile);
            
                // Table Insert
                BoardFileDto boardFileDto = new BoardFileDto();
                boardFileDto.setBoardId(boardId);
                boardFileDto.setFileName(fileName);
                boardFileDto.setFileSize(part.getSize());
                boardFileDto.setFileContentType(part.getContentType());
                boardFileDto.setFileUUID(fileUUID);

                dao.boardFileInsert(boardFileDto);
            }

            boardResultDto.setResult(SUCCESS);
        }catch(Exception e) {
            e.printStackTrace();

            for(File file : rollbackFileList) {    
                if(file.exists()) {
                    file.delete();
                }
            }
            
            boardResultDto.setResult(FAIL);
        }
        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request){
        
        BoardResultDto boardResultDto = new BoardResultDto();

        List<File> rollbackFileList = new ArrayList<>();
        
        try {
            dao.boardUpdate(dto);

            //
            List<MultipartFile> fileList = request.getFiles("file");
            
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            
            List<String> fileDeleteList = dao.boardFileDeleteList(dto.getBoardId());    
            for(String fileUUID : fileDeleteList) {    
                File file = new File(uploadPath + File.separator + fileUUID);
                if(file.exists()) {
                    file.delete();
                }
            }

            dao.boardFileDelete(dto.getBoardId());
            
            for (MultipartFile part : fileList) {
                int boardId = dto.getBoardId();
                
                String fileName = part.getOriginalFilename();
                
                //Random File Id
                UUID uuid = UUID.randomUUID();
                
                //file extension
                String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
                String fileUUID = uuid + "." + extension;
                
                File saveFile = new File(uploadPath + File.separator + fileUUID);

                rollbackFileList.add(saveFile);
                
                part.transferTo(saveFile);
            
                // Table Insert
                BoardFileDto boardFileDto = new BoardFileDto();
                boardFileDto.setBoardId(boardId);
                boardFileDto.setFileName(fileName);
                boardFileDto.setFileSize(part.getSize());
                boardFileDto.setFileContentType(part.getContentType());
                boardFileDto.setFileUUID(fileUUID);
                
                dao.boardFileInsert(boardFileDto);
            }

            boardResultDto.setResult(SUCCESS);
            
        }catch(Exception e) {
            e.printStackTrace();

            for(File file : rollbackFileList) {    
                if(file.exists()) {
                    file.delete();
                }
            }
            
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto boardDelete(int boardId) {
        
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {

            List<String> fileDeleteList = dao.boardFileDeleteList(boardId);    
            
            dao.boardFileDelete(boardId);
            dao.boardReadCountDelete(boardId);
            dao.boardDelete(boardId);        
            boardResultDto.setResult(SUCCESS);

            for(String fileUUID : fileDeleteList) {    
                File file = new File(uploadPath + File.separator + fileUUID);
                if(file.exists()) {
                    file.delete();
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }
}