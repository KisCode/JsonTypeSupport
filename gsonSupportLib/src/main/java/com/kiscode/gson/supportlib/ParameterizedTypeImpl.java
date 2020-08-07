package com.kiscode.gson.supportlib;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Description:
 * Author: KrisKeno
 * Date : 2020/8/8 9:53 PM
 **/
public class ParameterizedTypeImpl implements ParameterizedType {
    private final Class rawType;
    private final Type[] actualTypeArguments;

    public ParameterizedTypeImpl(Class rawType, Type[] actualTypeArguments) {
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
