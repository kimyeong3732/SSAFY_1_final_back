package com.mycom.springboot.user.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.springboot.user.dao.UserDao;
import com.mycom.springboot.user.dto.UserDto;
import com.mycom.springboot.user.dto.UserFileDto;
import com.mycom.springboot.user.dto.UserResultDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Value("${app.fileupload.upload.path}")
    String uploadPath;
	private final UserDao userDao;
	
	@Override
	public UserResultDto userRegister(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if(userDao.userRegister(userDto) == 1) {
			int userSeq = userDao.getSeq(userDto);
			if(userDao.setImg(userSeq) == 1) {
				userResultDto.setResult("success");
			}
			else {
				userDao.userDelete(userSeq);
				userResultDto.setResult("fail");
			}
		}
		else {
			userResultDto.setResult("fail");
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userUpdate(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if(userDao.userUpdate(userDto) == 1) {
			userResultDto.setResult("success");
		}
		else {
			userResultDto.setResult("fail");
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userDelete(String userEmail) {
		UserDto userDto = new UserDto();
		userDto.setUserEmail(userEmail);
		
		int userSeq = userDao.getSeq(userDto);
		
		UserResultDto userResultDto = new UserResultDto();
		
		List<Integer> boardIds = userDao.getUserBoard(userSeq);
		
		for(int boardId: boardIds) {
			userDao.deleteBoardFile(boardId);
		}
		userDao.deleteBoardUser(userSeq);
		userDao.deleteBoard(userSeq);
		
		userDao.deleteFavorite(userSeq);
		userDao.deletePrev(userSeq);
		userDao.deleteFriend(userSeq);
		userDao.deleteUserFile(userSeq);
		userDao.deleteUser(userSeq);
		
		userResultDto.setResult("success");
		
		return userResultDto;
	}

	@Override
	public UserResultDto updateImg(UserFileDto userDto, MultipartHttpServletRequest request) {
		

		UserResultDto userResultDto = new UserResultDto();
        
        try {
            MultipartFile file = request.getFile("file");
    
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            
            int userSeq = userDto.getUserSeq();
            
            String img = userDao.getImg(userSeq);
            
            if(img!=null && !"noProfile.png".equals(img)) {
            	File fileimg = new File(uploadPath + File.separator + img);
                if(fileimg.exists()) {
                	fileimg.delete();
                }
            }
            
            String fileName = file.getOriginalFilename();
            
            //Random File Id
            UUID uuid = UUID.randomUUID();
            
            //file extension
            String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
        
            String fileUUID = uuid + "." + extension;
        
            File saveFile = new File(uploadPath + File.separator + fileUUID);
            
            file.transferTo(saveFile);
        
            // Table Insert
            UserFileDto userFileDto = new UserFileDto();
            userFileDto.setUserSeq(userSeq);
            userFileDto.setFileName(fileName);
            userFileDto.setFileSize(file.getSize());
            userFileDto.setFileContentType(file.getContentType());
            userFileDto.setFileUUID(fileUUID);

            userDao.updateImg(userFileDto);

            userResultDto.setResult("success");
        }catch(Exception e) {
            e.printStackTrace();
            
            userResultDto.setResult("fail");
        }
        return userResultDto;
	}
}
