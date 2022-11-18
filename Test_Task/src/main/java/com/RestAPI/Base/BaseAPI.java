package com.RestAPI.Base;

import com.aventstack.extentreports.Status;
import com.softwaretestingboard.magento.util.Utility;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {

    private Utility utility = new Utility();
    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public HTTP_METHOD getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HTTP_METHOD httpMethod) {
        this.httpMethod = httpMethod;
    }

    public RequestSpecBuilder getSpecBuilder() {
        return specBuilder;
    }

    private String baseUri;
    private String basePath;
    private BaseAPI.HTTP_METHOD httpMethod;
    private RequestSpecBuilder specBuilder=new RequestSpecBuilder();

    public static enum HTTP_METHOD
    {
        POST,
        GET,
        PUT,
        PATCH,
        DELETE;

        private HTTP_METHOD() {
        }
    }

    public Response execute()
    {
        RequestSpecification requestSpecification=this.specBuilder.build();
        utility.commonLogging(Status.INFO,"Sending HTTP "+this.httpMethod+" Request to URI "+ getBaseUri()+getBasePath());
        switch (this.httpMethod){

            case GET:
                return (Response) RestAssured.given().spec(requestSpecification).when().get();

            case POST:
                return (Response)RestAssured.given().spec(requestSpecification).when().post();

            case PUT:
                return (Response)RestAssured.given().spec(requestSpecification).when().put();

            case PATCH:
                return (Response)RestAssured.given().spec(requestSpecification).when().patch();

            case DELETE:
                return (Response)RestAssured.given().spec(requestSpecification).when().delete();
            default:
                return null;
        }
    }


}

