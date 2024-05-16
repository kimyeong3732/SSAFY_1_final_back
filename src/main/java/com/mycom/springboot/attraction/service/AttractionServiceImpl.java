package com.mycom.springboot.attraction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.springboot.attraction.dao.AttractionDao;
import com.mycom.springboot.attraction.dto.AttractionDto;
import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.attraction.dto.SidoCodeDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{

	private final AttractionDao attractionDao;
	
	@Override
	public ArrayList<SearchDto> search(AttractionDto attractionDto) {
		return attractionDao.search(attractionDto);
	}

	@Override
	public List<SidoCodeDto> sidoCodeList() {
		return attractionDao.sidoCodeList();
	}

}
