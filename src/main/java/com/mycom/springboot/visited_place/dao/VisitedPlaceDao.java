package com.mycom.springboot.visited_place.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.visited_place.dto.VisitedPlaceDto;

@Mapper
public interface VisitedPlaceDao {
    void addVisitedPlace(VisitedPlaceDto visitedPlaceDto);
    void removeVisitedPlace(int visitedPlaceId);
    ArrayList<SearchDto> getVisitedPlacesByUser(int userSeq);
    boolean existsByUserSeqAndContentId(int userSeq, int attractionId);
}
