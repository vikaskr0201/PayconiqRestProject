package com.payconiq.test;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.payconiq.client.BookingClient;
import com.payconiq.config.Config;
import com.payconiq.config.HttpStatusCode;
import com.payconiq.model.*;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import com.payconiq.client.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WithTags({
        @WithTag("type:regression")
})

public class PayconiqTest {
    private Gson gson;
    private BookingClient bookingclient;
    private Faker faker;
    private String username;
    private String password;
    private int value =0;
    private BookingDates bookingDates;
    private static String token;
    private static String firstName;
    private static String lastName;
    private static String updateFirstName;
    private static String updateLastName;
    private static String checkInDate;
    private static String checkoutDate;
    private static int totalPrice;
    private boolean depositPaid;
    private static String additionalNeeds;
    private static int  bookingId;
    private final Logger logger = LoggerFactory.getLogger(PayconiqTest.class);

    public PayconiqTest() {
        this.bookingclient = new BookingClient();
        this.gson = new Gson();
    }
    @Title("CreateToken - Authorization Generating Token")
    @Test
    @WithTag("type:smoke")
    public void testA() throws Exception {
        username = Config.username;
        password= Config.password;
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(username);
        authRequest.setPassword(password);
        String requestBody =gson.toJson(authRequest);
        Response response = this.bookingclient.authorization(requestBody);
        token= response.jsonPath().getString("token");
        Assert.assertEquals(HttpStatusCode.OK, response.getStatusCode());
        Assert.assertThat(token, CoreMatchers.notNullValue());
        logger.info("Generated token is :" +token);

    }

    @Title("CreateBooking - Creating a Successful Booking")
    @Test
    @WithTag("type:smoke")
    public void testB() throws Exception {
        this.faker =new Faker();
        firstName= faker.name().firstName();
        lastName = faker.name().firstName();
        totalPrice = faker.number().numberBetween(10, 10000);
        checkInDate= LocalDate.now().toString();
        checkoutDate= LocalDate.now().plusDays(7).toString();
        bookingDates = new BookingDates(checkInDate, checkoutDate);
        depositPaid = true;
        additionalNeeds = faker.food().spice();

        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setBookingdates(bookingDates);
        bookingRequest.setFirstname(firstName);
        bookingRequest.setLastname(lastName);
        bookingRequest.setTotalprice(totalPrice);
        bookingRequest.setDepositpaid(depositPaid);
        bookingRequest.setBookingdates(bookingDates);
        bookingRequest.setAdditionalneeds(additionalNeeds);

        String postJsonBody = gson.toJson(bookingRequest);
        HttpResponse<BookingResponse> httpResponse= this.bookingclient.createBooking(postJsonBody);
        Assert.assertThat(httpResponse.getModel().getBooking().getFirstname(), CoreMatchers.containsString(firstName));
        Assert.assertThat(httpResponse.getModel().getBookingid(), CoreMatchers.notNullValue());
        Assert.assertEquals(HttpStatusCode.OK, httpResponse.getResponse().getStatusCode());
        bookingId= httpResponse.getModel().getBookingid();
        logger.info("Booking ID Generated After Booking is" +bookingId);


    }

    @Title("Get the Booking By ID and validate parameters for that booking ID")
    @Test
    @WithTag("type:smoke")
    public void testC() throws Exception {
        logger.info("Booking ID Passed for the Get Booking: " +bookingId);
        HttpResponse<GetBookingResponse> httpResponse= this.bookingclient.getBookingById(bookingId);
        Assert.assertEquals(HttpStatusCode.OK, httpResponse.getResponse().getStatusCode());
        logger.info("Validating the response for first name");
        Assert.assertThat(httpResponse.getModel().getFirstname(), CoreMatchers.containsString(firstName));
        logger.info("Validating the response for last name");
        Assert.assertThat(httpResponse.getModel().getLastname(), CoreMatchers.containsString(lastName));
        logger.info("Validating the response for additional name");
        Assert.assertThat(httpResponse.getModel().getAdditionalneeds(), CoreMatchers.containsString(additionalNeeds));

    }


    @Title("Get the Booking Details and validate it's not null")
    @Test
    @WithTag("type:smoke")
    public void testD() throws Exception {
        Response response= this.bookingclient.getBooking();
        int value= response.jsonPath().getInt("[0].bookingid");
        logger.info("Booking ID is "+value);
        Assert.assertEquals(HttpStatusCode.OK, response.getStatusCode());
        Assert.assertThat(value, CoreMatchers.notNullValue());

    }

    @Title("Get the Booking Details Filter by Name")
    @Test
    public void testE() throws Exception {

        Response response= this.bookingclient.getBookingFilterByName(firstName, lastName);
        int value= response.jsonPath().getInt("[0].bookingid");
        logger.info("Booking ID is "+value);
        Assert.assertEquals(HttpStatusCode.OK, response.getStatusCode());
        Assert.assertThat(value, CoreMatchers.is(bookingId));

    }

   @Title("Get the Booking Details Filter by CheckIn/CheckOut Date")
   @Test
    public void testF() throws Exception {
       boolean thrown = true;
       Response response= this.bookingclient.getBookingFilterByCheckDate(checkInDate, checkoutDate);
       String str= response.body().asString();
      try {
           value= response.jsonPath().getInt("[0].bookingid") ;
       }
      catch(NullPointerException e)
       {
           logger.info("NullPointerException caught check the response body");
           thrown =false;
       }
      Assert.assertTrue(thrown);
      logger.info("Response Body is:"+str);
      logger.info("Booking ID is "+value);
       Assert.assertEquals(HttpStatusCode.OK, response.getStatusCode());
      Assert.assertThat(value, CoreMatchers.is(bookingId));

   }

    @Title("Partial Update - Updating first name and last name")
    @Test
    @WithTag("type:smoke")
    public void testG() throws Exception {
        this.faker =new Faker();
        updateFirstName= faker.name().firstName();
        updateLastName = faker.name().firstName();
        PartialUpdateRequest partialUpdateRequest = new PartialUpdateRequest();
        logger.info("Booking ID for which partial update is done : "+bookingId);
        partialUpdateRequest.setFirstname(updateFirstName);
        partialUpdateRequest.setLastname(updateLastName);
        String postJsonBody = gson.toJson(partialUpdateRequest);
        Response response= this.bookingclient.partialUpdateBooking(bookingId,token,postJsonBody);
        Assert.assertEquals(HttpStatusCode.OK, response.getStatusCode());
        Assert.assertThat(response.jsonPath().getString("firstname"), CoreMatchers.containsString(updateFirstName));
        Assert.assertThat(response.jsonPath().getString("lastname"), CoreMatchers.containsString(updateLastName));


    }

    @Title("Delete Booking with wrong token not allowed")
    @Test
    public void testH() throws Exception {

        Response response= this.bookingclient.deleteBookingWrongToken(bookingId,token);
        Assert.assertEquals(HttpStatusCode.FORBIDDEN, response.getStatusCode());

    }

    @Title("Delete Booking ")
    @Test
    @WithTag("type:smoke")
    public void testI() throws Exception {

        Response response= this.bookingclient.deleteBooking(bookingId,token);
        Assert.assertEquals(HttpStatusCode.CREATED, response.getStatusCode());
        Assert.assertThat(response.getBody().asString(), CoreMatchers.containsString("Created"));

    }

    @Title("Updating a booking ID which is already deleted should not be allowed")
    @Test
    public void testJ() throws Exception {

        this.faker =new Faker();
        updateFirstName= faker.name().firstName();
        updateLastName = faker.name().firstName();
        PartialUpdateRequest partialUpdateRequest = new PartialUpdateRequest();
        logger.info("Deleted booking ID is  : "+bookingId);
        partialUpdateRequest.setFirstname(updateFirstName);
        partialUpdateRequest.setLastname(updateLastName);
        String postJsonBody = gson.toJson(partialUpdateRequest);
        Response response= this.bookingclient.partialUpdateBooking(bookingId,token,postJsonBody);
        Assert.assertEquals(HttpStatusCode.METHOD_NOT_ALLOWED, response.getStatusCode());


    }

}
