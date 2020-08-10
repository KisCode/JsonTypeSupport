package com.kiscode.gson.supportlib;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Description: ParameterizedTypeImp
 * Author: KrisKeno
 * Date : 2020/8/10 9:35 PM
 **/
public class ParameterizedTypeImp implements ParameterizedType {
    //对象类型 相当于List<String> 的List
    private Type rawType;
    // 泛型类型 相当于List<String> 的String
    private Type[] actualTypeArguments;

    public ParameterizedTypeImp(Type rawType,Type[] actualTypeArguments) {
        this.rawType = rawType;
        this.actualTypeArguments = actualTypeArguments;
    }

    @NonNull
    @Override
    public Type[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    @NonNull
    @Override
    public Type getRawType() {
        return rawType;
    }

    @Nullable
    @Override
    public Type getOwnerType() {
        return null;
    }
}
