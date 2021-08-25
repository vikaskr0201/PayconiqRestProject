package com.payconiq.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PartialUpdateRequest {

    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;


    /**
     * No args constructor for use in serialization
     *
     */
    public PartialUpdateRequest() {
    /*    this.firstname = faker.name().firstName();
        this.lastname = faker.name().firstName();
        this.totalprice = faker.number().numberBetween(10, 10000);
        this.bookingdates = new BookingDates(LocalDate.now().toString(), LocalDate.now().plusDays(7).toString());
        this.depositpaid = true;
        this.additionalneeds = faker.food().spice();*/
    }

    /**
     *
     * @param firstname
     * @param lastname
     */
    public PartialUpdateRequest(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;

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


    @Override
    public String toString() {

        return new ToStringBuilder(this).append("firstname", firstname)
                .append("lastname", lastname)
                .toString();
    }

}