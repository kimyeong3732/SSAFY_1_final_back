package com.mycom.springboot.visited_place.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VisitedResultDto {
	private VisitedPlaceDto visitedPlaceDto;
	private List<VisitedPlaceDto> list;
	private String result; // success, fail 등
}
