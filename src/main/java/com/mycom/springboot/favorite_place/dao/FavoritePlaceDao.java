package com.mycom.springboot.favorite_place.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.dto.FavoritePlaceDto;

@Mapper
public interface FavoritePlaceDao {
    void addFavoritePlace(FavoritePlaceDto favoritePlaceDto);
    void removeFavoritePlace(int favoritePlaceId);
    ArrayList<SearchDto> getFavoritePlacesByUser(int userSeq);
    boolean existsByUserSeqAndContentId(int userSeq, int attractionId);
}
