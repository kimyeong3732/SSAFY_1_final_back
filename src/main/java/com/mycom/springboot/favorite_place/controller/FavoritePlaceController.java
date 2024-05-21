package com.mycom.springboot.favorite_place.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.favorite_place.dto.FavoritePlaceDto;
import com.mycom.springboot.favorite_place.exception.DuplicateFavoritePlaceException;
import com.mycom.springboot.favorite_place.service.FavoritePlaceServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class FavoritePlaceController {
    private final FavoritePlaceServiceImpl favoritePlaceService;

    @PostMapping
    public ResponseEntity<String> addFavoritePlace(@RequestBody FavoritePlaceDto favoritePlaceDto) {
        try {
            favoritePlaceService.addFavoritePlace(favoritePlaceDto.getUserSeq(), favoritePlaceDto.getAttractionId());
            return ResponseEntity.ok("Favorite place added successfully");
        } catch (DuplicateFavoritePlaceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add favorite place");
        }
    }

    @DeleteMapping("/{favoritePlaceId}")
    public ResponseEntity<String> removeFavoritePlace(@PathVariable("favoritePlaceId") int favoritePlaceId) {
        try {
            favoritePlaceService.removeFavoritePlace(favoritePlaceId);
            return ResponseEntity.ok("Favorite place removed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove favorite place");
        }
    }

    @GetMapping("/favorite/{userSeq}")
    public ResponseEntity<ArrayList<SearchDto>> getFavoritePlacesByUser(@PathVariable("userSeq") int userSeq) {
    	log.info("??");
        try {
        	ArrayList<SearchDto> favoritePlaces = favoritePlaceService.getFavoritePlacesByUser(userSeq);
            return ResponseEntity.ok(favoritePlaces);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
