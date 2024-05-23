package com.mycom.springboot.visited_place.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.dto.FavoriteResultDto;
import com.mycom.springboot.visited_place.dto.VisitedPlaceDto;
import com.mycom.springboot.visited_place.dto.VisitedResultDto;
import com.mycom.springboot.visited_place.exception.DuplicateVisitedPlaceException;
import com.mycom.springboot.visited_place.service.VisitedPlaceService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class VisitedPlaceController {
    private final VisitedPlaceService visitedPlaceService;

    @PostMapping(value="/visited")
    public ResponseEntity<String> addVisitedPlace(@RequestBody VisitedPlaceDto visitedPlaceDto) {
    	try {
        	VisitedResultDto visitedResultDto = visitedPlaceService.addVisitedPlace(visitedPlaceDto);
        	
        	log.info(visitedPlaceDto.getUserSeq()+"");
        	log.info(visitedPlaceDto.getAttractionId()+"");
        	
        	if("success".equals(visitedResultDto.getResult())) {
                return ResponseEntity.ok("Visited place added successfully");
        	} return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add visited place");
        } catch (DuplicateVisitedPlaceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add visited place");
        }
    }

    @DeleteMapping(value="/visitedDelete")
    public VisitedResultDto deleteVisitedPlace(@RequestBody VisitedPlaceDto visitedPlaceDto) {
    	log.info("visitedDelete");
    	VisitedResultDto visitedResultDto = visitedPlaceService.deleteVisitedPlace(visitedPlaceDto);
    	
    	return visitedResultDto;
    }

    @GetMapping("/visited/{userSeq}")
    public ResponseEntity<ArrayList<SearchDto>> getVisitedPlacesByUser(@PathVariable("userSeq") int userSeq) {
    	log.info("??");
        try {
        	ArrayList<SearchDto> visitedPlaces = visitedPlaceService.getVisitedPlacesByUser(userSeq);
            return ResponseEntity.ok(visitedPlaces);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
