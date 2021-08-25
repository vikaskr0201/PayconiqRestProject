package com.payconiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GetBookingResponse {

    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("totalprice")
    @Expose
    private Integer totalprice;
    @SerializedName("depositpaid")
    @Expose
    private Boolean depositpaid;
    @SerializedName("bookingdates")
    @Expose
    private BookingDates bookingdates;
    @SerializedName("additionalneeds")
    @Expose
    private String additionalneeds;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetBookingResponse() {
    }

    /**
     *
     * @param firstname
     * @param additionalneeds
     * @param bookingdates
     * @param totalprice
     * @param depositpaid
     * @param lastname
     */
    public GetBookingResponse(String firstname, String lastname, Integer totalprice, Boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("firstname", firstname)
                .append("lastname", lastname)
                .append("totalprice", totalprice)
                .append("depositpaid", depositpaid)
                .append("bookingdates", bookingdates)
                .append("additionalneeds", additionalneeds)
                .toString();
    }
}

