package com.mycom.springboot.favorite_place.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.dao.FavoritePlaceDao;
import com.mycom.springboot.favorite_place.dto.FavoritePlaceDto;
import com.mycom.springboot.favorite_place.exception.DuplicateFavoritePlaceException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavoritePlaceServiceImpl implements FavoritePlaceService{
    private final FavoritePlaceDao favoritePlaceDao;
    
    @Override
    public void addFavoritePlace(int userSeq, int attractionId) {
        if (favoritePlaceDao.existsByUserSeqAndContentId(userSeq, attractionId)) {
            throw new DuplicateFavoritePlaceException("Favorite place already exists for the user and content");
        }

        FavoritePlaceDto favoritePlaceDto = new FavoritePlaceDto();
        favoritePlaceDto.setUserSeq(userSeq);
        favoritePlaceDto.setAttractionId(attractionId);
        favoritePlaceDao.addFavoritePlace(favoritePlaceDto);
    }
    
    @Override
    public void removeFavoritePlace(int favoritePlaceId) {
        favoritePlaceDao.removeFavoritePlace(favoritePlaceId);
    }

    @Override
    public ArrayList<SearchDto> getFavoritePlacesByUser(int userSeq) {
        return favoritePlaceDao.getFavoritePlacesByUser(userSeq);
    }
}
