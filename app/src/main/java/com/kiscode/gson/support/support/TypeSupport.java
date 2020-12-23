package com.kiscode.gson.support.support;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description: 简陋版Gson TypeToken
 * Author: keno
 * Date : 2020/12/22 11:38
 **/
public class TypeSupport<T> {
    final Type type;

    protected TypeSupport() {

        //getGenericSuperclass()获得带有类型的父类  TypeSupport<ReturnModel<List<Student>>>
        //Type是Java编程语言中所有类型的公共高级接口，它们包括原始类型，参数化类型，数组类型，类型变量和基本类型
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new IllegalArgumentException("Missing type parameter.");
        }

        //getActualTypeArguments() 获取泛型类型TypeSupport<ReturnModel<List<Student>>> 中的ReturnModel<List<Student>>
        this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];;
    }

    public final Type getType() {
        return type;
    }
}