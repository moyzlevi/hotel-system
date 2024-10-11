package com.molevi.notification_service.util;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ToNumberPolicy;

import java.lang.reflect.Type;

public class JsonUtils {
    public static <T> T fromJson(String json, Type typeToken) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE);
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Gson gson = gsonBuilder.create();

        try {
            return gson.fromJson(json, typeToken);
        } catch (JsonSyntaxException var5) {
            return null;
        }
    }
}
