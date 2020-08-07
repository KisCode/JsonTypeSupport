package com.kiscode.gson.supportlib;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Description: Gson解析工具类
 * Author: KrisKeno
 * Date : 2020/8/8 6:25 PM
 **/
public class GsonUtil {
    public static <T> T fromJsonToObject(String json, Class<T> clz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clz);
    }

    public static <T> T fromJsonToObject(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}
