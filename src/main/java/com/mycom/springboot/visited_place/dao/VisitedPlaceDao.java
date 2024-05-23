package com.mycom.springboot.visited_place.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.visited_place.dto.VisitedPlaceDto;

@Mapper
public interface VisitedPlaceDao {
	int addVisitedPlace(VisitedPlaceDto visitedPlaceDto);
	int deleteVisitedPlace(VisitedPlaceDto visitedPlaceDto);
    ArrayList<SearchDto> getVisitedPlacesByUser(int userSeq);
    boolean existsByUserSeqAndContentId(VisitedPlaceDto visitedPlaceDto);
}
