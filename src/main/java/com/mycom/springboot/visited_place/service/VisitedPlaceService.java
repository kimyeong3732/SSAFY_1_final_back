package com.mycom.springboot.visited_place.service;

import java.util.ArrayList;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.visited_place.dto.VisitedPlaceDto;
import com.mycom.springboot.visited_place.dto.VisitedResultDto;

public interface VisitedPlaceService {

	VisitedResultDto addVisitedPlace(VisitedPlaceDto visitedPlaceDto);
	VisitedResultDto deleteVisitedPlace(VisitedPlaceDto visitedPlaceDto);
	ArrayList<SearchDto> getVisitedPlacesByUser(int userSeq);
    
}
