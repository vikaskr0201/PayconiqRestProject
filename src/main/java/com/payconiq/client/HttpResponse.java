package com.payconiq.client;
import io.restassured.response.Response;

public class HttpResponse<M> {

    private Response response;
    private M model;

    public HttpResponse(Response response, M model) {
        this.response = response;
        this.model = model;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public M getModel() {
        return model;
    }

    public void setS(M model) {
        this.model = model;
    }
}