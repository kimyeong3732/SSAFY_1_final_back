package com.mycom.springboot.favorite_place.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.dao.FavoritePlaceDao;
import com.mycom.springboot.favorite_place.dto.FavoritePlaceDto;
import com.mycom.springboot.favorite_place.dto.FavoriteResultDto;
import com.mycom.springboot.favorite_place.exception.DuplicateFavoritePlaceException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavoritePlaceServiceImpl implements FavoritePlaceService{
    private final FavoritePlaceDao favoritePlaceDao;
    
    @Override
    public FavoriteResultDto addFavoritePlace(FavoritePlaceDto favoritePlaceDto) {
    	FavoriteResultDto favoriteResultDto = new FavoriteResultDto();

    	if (favoritePlaceDao.existsByUserSeqAndContentId(favoritePlaceDto)) {
    		favoriteResultDto.setResult("fail");
    		throw new DuplicateFavoritePlaceException("Favorite place already exists for the user and content");
        } else {
        	if(favoritePlaceDao.addFavoritePlace(favoritePlaceDto) == 1) {
        		favoriteResultDto.setResult("success");
        	} else {
            	favoriteResultDto.setResult("fail");
        	}
        }

        return favoriteResultDto;
    }
    
    @Override
    public FavoriteResultDto deleteFavoritePlace(FavoritePlaceDto favoritePlaceDto) {
    	FavoriteResultDto favoriteResultDto = new FavoriteResultDto();
    	
    	if(favoritePlaceDao.deleteFavoritePlace(favoritePlaceDto)==1) {
    		favoriteResultDto.setResult("success");
    	} else {
    		favoriteResultDto.setResult("fail");
    	}
    	
    	return favoriteResultDto;
    }

    @Override
    public ArrayList<SearchDto> getFavoritePlacesByUser(int userSeq) {
        return favoritePlaceDao.getFavoritePlacesByUser(userSeq);
    }
}
