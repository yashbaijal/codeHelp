package com.RestAPI.helper;

import com.RestAPI.Pojos.response.CreateUserResponse;
import com.RestAPI.Pojos.response.Info;
import com.RestAPI.Pojos.response.Results;
import com.RestAPI.apis.CreateUser;
import com.RestAPI.utilities.APIManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.softwaretestingboard.magento.util.Utility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class APIhelper {

    private Utility utility = new Utility();
    public static Response excecuteAndGetResponse()
    {
        CreateUser createUser=new CreateUser();
        return createUser.execute();
    }

    public static CreateUserResponse getResponseObj(Response responseJson)
    {
        return (CreateUserResponse) APIManager.convertFromJson(responseJson.asString(), CreateUserResponse.class);
    }


}
