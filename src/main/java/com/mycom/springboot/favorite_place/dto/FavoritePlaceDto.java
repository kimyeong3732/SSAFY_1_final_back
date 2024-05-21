package com.mycom.springboot.favorite_place.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FavoritePlaceDto {
    private int favoritePlaceId;
    private int userSeq;
    private int attractionId;
}
