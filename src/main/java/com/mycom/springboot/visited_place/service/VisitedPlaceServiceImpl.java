package com.mycom.springboot.visited_place.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.exception.DuplicateFavoritePlaceException;
import com.mycom.springboot.visited_place.dao.VisitedPlaceDao;
import com.mycom.springboot.visited_place.dto.VisitedPlaceDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VisitedPlaceServiceImpl implements VisitedPlaceService{
    private final VisitedPlaceDao visitedPlaceDao;
    
    @Override
    public void addVisitedPlace(int userSeq, int attractionId) {
        if (visitedPlaceDao.existsByUserSeqAndContentId(userSeq, attractionId)) {
            throw new DuplicateFavoritePlaceException("Visited place already exists for the user and content");
        }

        VisitedPlaceDto visitedPlaceDto = new VisitedPlaceDto();
        visitedPlaceDto.setUserSeq(userSeq);
        visitedPlaceDto.setAttractionId(attractionId);
        visitedPlaceDao.addVisitedPlace(visitedPlaceDto);
    }
    
//    @Override
//    public void removeFavoritePlace(int favoritePlaceId) {
//        favoritePlaceDao.removeFavoritePlace(favoritePlaceId);
//    }

    @Override
    public ArrayList<SearchDto> getVisitedPlacesByUser(int userSeq) {
        return visitedPlaceDao.getVisitedPlacesByUser(userSeq);
    }
}
