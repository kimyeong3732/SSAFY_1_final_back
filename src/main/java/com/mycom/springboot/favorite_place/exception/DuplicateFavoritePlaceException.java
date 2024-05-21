package com.mycom.springboot.favorite_place.exception;

public class DuplicateFavoritePlaceException extends RuntimeException {
    public DuplicateFavoritePlaceException(String message) {
        super(message);
    }
}
