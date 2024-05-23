package com.mycom.springboot.favorite_place.service;

import java.util.ArrayList;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.dto.FavoritePlaceDto;
import com.mycom.springboot.favorite_place.dto.FavoriteResultDto;

public interface FavoritePlaceService {
	FavoriteResultDto addFavoritePlace(FavoritePlaceDto favoritePlaceDto);
	FavoriteResultDto deleteFavoritePlace(FavoritePlaceDto favoritePlaceDto);
	ArrayList<SearchDto> getFavoritePlacesByUser(int userSeq);
}
