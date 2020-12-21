package com.kiscode.gson.support;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description:
 * Author: keno
 * Date : 2020/12/18 8:20
 **/
public abstract class BaseCallBack<T> implements ICallBack<T> {

    private static final Gson DEFAULT_GSON = new Gson();

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public T convert(String json) {
        //获取父类泛型type, 如: BaseCallBack<ReturnModel<List<Student>>>
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass != null) {
            Type[] params = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            return DEFAULT_GSON.fromJson(json, params[0]);
        }
        return null;
    }
}