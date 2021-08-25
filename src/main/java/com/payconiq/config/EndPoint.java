package com.payconiq.config;

public interface EndPoint {

    String CREATE_BOOKING = "/booking";
    String GET_BOOKING_ID ="/booking/{bookingId}";
    String GET_BOOKING ="/booking";
    String AUTH="/auth";
    String UPDATE_BOOKING="/booking/{bookingId}";
    String DELETE_BOOKING="/booking/{bookingId}";
}
