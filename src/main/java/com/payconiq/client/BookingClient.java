package com.payconiq.client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.payconiq.config.Config;
import com.payconiq.config.EndPoint;
import com.payconiq.model.BookingResponse;
import com.payconiq.model.GetBookingResponse;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.rest.SerenityRest.given;

public class BookingClient {

    private Gson gson;
    private final Logger logger = LoggerFactory.getLogger(BookingClient.class);
    public BookingClient(){
        this.gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
    }

    public Response authorization (String requestBody) {
        String url = Config.baseUrl.concat(EndPoint.AUTH);
        System.out.println("URL is -->"+url);
        Response response= given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(url);
        logger.info("Authorization Response is :" + response.asString());
        //     System.out.println(response.asString());
        return response;

    }

    public HttpResponse<BookingResponse> createBooking (String createBookingRequest) {
        String url = Config.baseUrl.concat(EndPoint.CREATE_BOOKING);
        System.out.println("URL is -->"+url);
        Response response= given()
                .header("Content-Type", "application/json")
                .body(createBookingRequest)
                .post(url);
        logger.info("Booking Response is :" + response.asString());
   //     System.out.println(response.asString());
        return new HttpResponse<BookingResponse>(response,
                gson.fromJson(response.getBody().asString(), BookingResponse.class));


    }

    public HttpResponse<GetBookingResponse> getBookingById (Integer bookingId) {
        String url = Config.baseUrl.concat(EndPoint.GET_BOOKING_ID);
        System.out.println("URL is -->"+url);
        Response response= given()
                .header("Content-Type", "application/json")
                .get(url,bookingId);
        logger.info("Get Booking Response is :" + response.asString());
        //     System.out.println(response.asString());
        return new HttpResponse<GetBookingResponse>(response,
                gson.fromJson(response.getBody().asString(), GetBookingResponse.class));


    }

    public Response getBooking () {
        String url = Config.baseUrl.concat(EndPoint.GET_BOOKING);
        System.out.println("URL is -->"+url);
        Response response= given()
                .header("Content-Type", "application/json")
                .get(url);
        logger.info("Get Booking Response is :" + response.asString());
        return response;

    }
    public Response getBookingFilterByName (String firstName, String lastName) {
        String url = Config.baseUrl.concat(EndPoint.GET_BOOKING);
        System.out.println("URL is -->"+url);
        Response response= given()
                .header("Content-Type", "application/json")
                .queryParam("firstname", firstName)
                .queryParam("lastname", lastName)
                .get(url);
        logger.info("Get Booking Response is :" + response.asString());
        return response;

    }

    public Response getBookingFilterByCheckDate (String checkInDate, String checkOutDate) {
        String url = Config.baseUrl.concat(EndPoint.GET_BOOKING);
        System.out.println("URL is -->"+url);
        System.out.println("Checking Date is "+checkInDate);
        System.out.println("Checkout Date is "+checkOutDate);
        Response response= given()
                .header("Content-Type", "application/json")
                .queryParam("checkin", checkInDate)
                .queryParam("checkout", checkOutDate)
                .get(url);
        logger.info("Get Booking Response is :" + response.asString());
        return response;

    }

    public Response partialUpdateBooking (Integer bookingId, String token, String partialUpdateRequest) {
        String url = Config.baseUrl.concat(EndPoint.UPDATE_BOOKING);
        System.out.println("URL is -->"+url);
        System.out.println("Booking ID is :  "+bookingId);
        String fullToken = "token="+token;
        System.out.println("Full token is: --"+fullToken);
        Response response= given()
                .header("Content-Type", "application/json")
                .header("Cookie",fullToken)
                .body(partialUpdateRequest)
                .patch(url,bookingId);
        logger.info("Booking Response is :" + response.asString());
        //     System.out.println(response.asString());
    return response;


    }

    public Response deleteBooking (Integer bookingId, String token) {
        String url = Config.baseUrl.concat(EndPoint.DELETE_BOOKING);
        System.out.println("URL is -->"+url);
        System.out.println("Booking ID is :  "+bookingId);
        String fullToken = "token="+token;
        System.out.println("Full token is: --"+fullToken);
        Response response= given()
                .header("Content-Type", "application/json")
                .header("Cookie",fullToken)
                .delete(url,bookingId);
        logger.info("Booking Response is :" + response.asString());
        //     System.out.println(response.asString());
        return response;
    }


    public Response deleteBookingWrongToken (Integer bookingId, String token) {
        String url = Config.baseUrl.concat(EndPoint.DELETE_BOOKING);
        System.out.println("URL is -->"+url);
        System.out.println("Booking ID is :  "+bookingId);
        String fullToken = "token=:"+token;
        logger.info("Wrong token is: --"+fullToken);
        Response response= given()
                .header("Content-Type", "application/json")
                .header("Cookie",fullToken)
                .delete(url,bookingId);
        logger.info("Booking Response is :" + response.asString());
        //     System.out.println(response.asString());
        return response;
    }




}
