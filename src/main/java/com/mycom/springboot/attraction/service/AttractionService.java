package com.mycom.springboot.attraction.service;

import java.util.ArrayList;
import java.util.List;

import com.mycom.springboot.attraction.dto.AttractionDto;
import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.attraction.dto.SidoCodeDto;

public interface AttractionService {
	ArrayList<SearchDto> search(	AttractionDto attractionDto);
	List<SidoCodeDto> sidoCodeList();
}
