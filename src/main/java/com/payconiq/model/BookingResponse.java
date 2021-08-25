package com.payconiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class BookingResponse {

    @SerializedName("bookingid")
    @Expose
    private Integer bookingid;
    @SerializedName("booking")
    @Expose
    private BookingRequest booking;

    /**
     * No args constructor for use in serialization
     *
     */
    public BookingResponse() {
    }

    /**
     *
     * @param booking
     * @param bookingid
     */
    public BookingResponse(Integer bookingid, BookingRequest booking) {
        super();
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingRequest getBooking() {
        return booking;
    }

    public void setBooking(BookingRequest booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("bookingid", bookingid)
                .append("booking", booking)
                .toString();
    }

}