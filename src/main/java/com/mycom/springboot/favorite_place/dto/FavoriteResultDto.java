package com.mycom.springboot.favorite_place.dto;

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
public class FavoriteResultDto {
	private FavoritePlaceDto favoritePlaceDto;
	private List<FavoritePlaceDto> list;
	private String result; // success, fail 등
}
