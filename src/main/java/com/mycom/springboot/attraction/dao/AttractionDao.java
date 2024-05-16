package com.mycom.springboot.attraction.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.springboot.attraction.dto.AttractionDto;
import com.mycom.springboot.attraction.dto.SearchDto;
import com.mycom.springboot.attraction.dto.SidoCodeDto;

@Mapper
public interface AttractionDao {

	ArrayList<SearchDto> search(AttractionDto attractionDto);
	List<SidoCodeDto> sidoCodeList();
}
