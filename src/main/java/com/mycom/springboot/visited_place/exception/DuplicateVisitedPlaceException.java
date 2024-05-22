package com.mycom.springboot.visited_place.exception;

public class DuplicateVisitedPlaceException extends RuntimeException {
    public DuplicateVisitedPlaceException(String message) {
        super(message);
    }
}
