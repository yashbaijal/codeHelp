package com.RestAPI.apis;

import com.RestAPI.Base.BaseAPI;
import com.RestAPI.helper.APIhelper;
import com.RestAPI.utilities.PropsLoader;

import java.util.ArrayList;
import java.util.List;

public class CreateUser  extends BaseAPI {


    public CreateUser()
    {
        setBasePath("/api");
        setBaseUri(PropsLoader.loadProprerties("base.Uri"));
        setHttpMethod(HTTP_METHOD.GET);
        getSpecBuilder().setBaseUri(PropsLoader.loadProprerties("base.Uri"));
        getSpecBuilder().setBasePath("/api");
        getSpecBuilder().addQueryParam("password","special,upper,lower,number");
    }
}
