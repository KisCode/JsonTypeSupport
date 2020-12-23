package com.kiscode.gson.support.support;

import java.lang.reflect.Type;

/**
 * Description: 简陋版Gson TypeToken
 * Author: keno
 * Date : 2020/12/22 11:38
 **/
public class TypeSupport<T> {
    final Type type;

    protected TypeSupport() {
        this.type = getClass().getGenericSuperclass();
    }

    public final Type getType() {
        return type;
    }
}