package com.mycom.springboot.visited_place.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.exception.DuplicateFavoritePlaceException;
import com.mycom.springboot.visited_place.dao.VisitedPlaceDao;
import com.mycom.springboot.visited_place.dto.VisitedPlaceDto;
import com.mycom.springboot.visited_place.dto.VisitedResultDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VisitedPlaceServiceImpl implements VisitedPlaceService{
    private final VisitedPlaceDao visitedPlaceDao;
    
    @Override
    public VisitedResultDto addVisitedPlace(VisitedPlaceDto visitedPlaceDto) {
    	VisitedResultDto visitedResultDto = new VisitedResultDto();

    	if (visitedPlaceDao.existsByUserSeqAndContentId(visitedPlaceDto)) {
    		visitedResultDto.setResult("fail");
    		throw new DuplicateFavoritePlaceException("Favorite place already exists for the user and content");
        } else {
        	if(visitedPlaceDao.addVisitedPlace(visitedPlaceDto) == 1) {
        		visitedResultDto.setResult("success");
        	} else {
        		visitedResultDto.setResult("fail");
        	}
        }

        return visitedResultDto;
    }
    
    @Override
    public VisitedResultDto deleteVisitedPlace(VisitedPlaceDto visitedPlaceDto) {
    	VisitedResultDto visitedResultDto = new VisitedResultDto();
    	
    	if(visitedPlaceDao.deleteVisitedPlace(visitedPlaceDto)==1) {
    		visitedResultDto.setResult("success");
    	} else {
    		visitedResultDto.setResult("fail");
    	}
    	
    	return visitedResultDto;
    }

    @Override
    public ArrayList<SearchDto> getVisitedPlacesByUser(int userSeq) {
        return visitedPlaceDao.getVisitedPlacesByUser(userSeq);
    }
}
