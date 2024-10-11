package com.molevi.hotel_service.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectToJson {

    private ObjectToJson() {}

    public static String toJson(Object requestObject) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();
        return gson.toJson(requestObject);
    }
}
