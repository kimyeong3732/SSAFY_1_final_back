package com.mycom.springboot.attraction.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.springboot.attraction.dto.AttractionDto;
import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.attraction.dto.SidoCodeDto;
import com.mycom.springboot.attraction.service.AttractionService;
import com.mycom.springboot.common.DistanceCalculator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AttractionController {

	private final AttractionService attractionService;
	
	/*
	 * @PostMapping("trip/search") public ResponseEntity<List<SearchDto>> search(
	 * 
	 * @RequestBody AttractionDto attractionDto ){ log.info("attractionDto: {}",
	 * attractionDto.getSidoCode()); List<SearchDto> list =
	 * attractionService.search(attractionDto); return ResponseEntity.ok(list); }
	 */
	
	@GetMapping("trip/search")
	public ResponseEntity<ArrayList<SearchDto>> search(	AttractionDto attractionDto){
//		log.info("attractioncode: {}", attractionDto.getCurLatitude());
//		log.info("attractioncontent: {}", attractionDto.getCurLongitude());
		 ArrayList<SearchDto> list = attractionService.search(attractionDto);
		 if(attractionDto.getChkSort()) {
			 DistanceCalculator.sortByDistance(list, attractionDto.getCurLatitude(), attractionDto.getCurLongitude());
		 }
		 for(SearchDto dto : list) {
			 System.out.println(dto);
		 }
		 return ResponseEntity.ok(list);
	}
	
	@GetMapping("trip/sidoCode")
	public ResponseEntity<List<SidoCodeDto>> sidoCodeList() {
		return ResponseEntity.ok(attractionService.sidoCodeList());
	}
	
}