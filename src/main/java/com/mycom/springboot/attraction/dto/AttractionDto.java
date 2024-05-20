package com.mycom.springboot.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttractionDto {
	private Integer sidoCode;
	private Integer attractionId;
	private String word;
	private Boolean chkSort;
	private Double curLatitude;
	private Double curLongitude;
	private Double latitude;
	private Double longitude;
}