package com.RestAPI.utilities;

import com.RestAPI.Pojos.response.Results;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIManager {

    public static String convertToJson(Object obj) {
        String jsonData = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonData =  mapper.writeValueAsString(obj);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static Object convertFromJson(String jsonData, Class<?> classType) {
        Object objFromJson = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            objFromJson = mapper.readValue(jsonData, classType);
        }
        catch(Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }

        return objFromJson;
    }
}
