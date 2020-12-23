package com.kiscode.gson.support.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description:
 * Author: keno
 * Date : 2020/12/18 8:20
 **/
public class BaseCallBack<T> implements ICallBack<T> {

    private static final Gson DEFAULT_GSON = new Gson();

    protected BaseCallBack() {
    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }



    @Override
    public T convert(String json) {
        //getSuperClass() 获得该类的父类  BaseCallBack的匿名内部类的父类 即BaseCallBack
        //getGenericSuperclass()获得带有类型的父类  即BaseCallBack<ReturnModel<List<Student>>>
        //Type是Java编程语言中所有类型的公共高级接口，它们包括原始类型，参数化类型，数组类型，类型变量和基本类型
        //获取带有泛型类型的父类type, 如: BaseCallBack<ReturnModel<List<Student>>>
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass != null) {
            Type[] params = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            return DEFAULT_GSON.fromJson(json, params[0]);
        }
        return null;
    }

}