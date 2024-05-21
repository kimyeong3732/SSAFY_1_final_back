package com.mycom.springboot.favorite_place.service;

import java.util.ArrayList;
import java.util.List;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.dto.FavoritePlaceDto;

public interface FavoritePlaceService {

	void addFavoritePlace(int userSeq, int attractionId);
	void removeFavoritePlace(int favoritePlaceId);
	ArrayList<SearchDto> getFavoritePlacesByUser(int userSeq);
    
}
