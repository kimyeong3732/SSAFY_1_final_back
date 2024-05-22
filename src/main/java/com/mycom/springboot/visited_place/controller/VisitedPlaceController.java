package com.mycom.springboot.visited_place.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.exception.DuplicateFavoritePlaceException;
import com.mycom.springboot.visited_place.dto.VisitedPlaceDto;
import com.mycom.springboot.visited_place.service.VisitedPlaceServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class VisitedPlaceController {
    private final VisitedPlaceServiceImpl visitedPlaceService;

    @PostMapping(value="/visited")
    public ResponseEntity<String> addVisitedPlace(@RequestBody VisitedPlaceDto visitedPlaceDto) {
        try {
        	visitedPlaceService.addVisitedPlace(visitedPlaceDto.getUserSeq(), visitedPlaceDto.getAttractionId());
            return ResponseEntity.ok("Favorite place added successfully");
        } catch (DuplicateFavoritePlaceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add favorite place");
        }
    }

//    @DeleteMapping("/{favoritePlaceId}")
//    public ResponseEntity<String> removeFavoritePlace(@PathVariable("favoritePlaceId") int favoritePlaceId) {
//        try {
//            favoritePlaceService.removeFavoritePlace(favoritePlaceId);
//            return ResponseEntity.ok("Favorite place removed successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove favorite place");
//        }
//    }

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
