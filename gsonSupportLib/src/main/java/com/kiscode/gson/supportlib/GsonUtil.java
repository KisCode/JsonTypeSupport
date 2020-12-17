package com.kiscode.gson.supportlib;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

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
        Class rawType = getRawType(type);
        Log.i("fromJsonToObject", "fromJsonToObject rawType:" + rawType.toString());
        return gson.fromJson(json, type);
    }


    static Class<?> getRawType(Type type) {
        if (type == null) throw new NullPointerException("type == null");

        if (type instanceof Class<?>) {
            // Type is a normal class.
            return (Class<?>) type;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            // I'm not exactly sure why getRawType() returns Type instead of Class. Neal isn't either but
            // suspects some pathological case related to nested classes exists.
            Type rawType = parameterizedType.getRawType();
            if (!(rawType instanceof Class)) throw new IllegalArgumentException();
            return (Class<?>) rawType;
        }
        if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getRawType(componentType), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            // We could use the variable's bounds, but that won't work if there are multiple. Having a raw
            // type that's more general than necessary is okay.
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        }

        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or "
                + "GenericArrayType, but <" + type + "> is of type " + type.getClass().getName());
    }
}
