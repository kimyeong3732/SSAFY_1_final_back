package com.mycom.springboot.favorite_place.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.dto.FavoritePlaceDto;

@Mapper
public interface FavoritePlaceDao {
    int addFavoritePlace(FavoritePlaceDto favoritePlaceDto);
    int deleteFavoritePlace(FavoritePlaceDto favoritePlaceDto);
    ArrayList<SearchDto> getFavoritePlacesByUser(int userSeq);
    boolean existsByUserSeqAndContentId(FavoritePlaceDto favoritePlaceDto);
}
