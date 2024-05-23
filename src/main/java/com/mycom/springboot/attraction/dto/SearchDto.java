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
public class SearchDto {
	private int attractionId;
	private String title;
	private String addr1;
	private String firstimage;
	private double latitude;
	private double longitude;
}
