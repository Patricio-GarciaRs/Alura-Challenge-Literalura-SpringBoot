package com.aluraChallenge.literalura.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class ApiServices {
    static API consumoAPI = new API();
    public static Map<String, Object> getJsonData(String url, String titulo){
        var json = consumoAPI.apiConnection(url+titulo.replace(" ", "%20"));
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}