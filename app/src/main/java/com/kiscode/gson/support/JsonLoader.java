package com.kiscode.gson.support;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.kiscode.gson.support.callback.ICallBack;
import com.kiscode.gson.support.model.ReturnModel;
import com.kiscode.gson.support.model.Student;
import com.kiscode.gson.support.support.TypeSupport;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Calendar;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2020/12/17 21:03
 */

public class JsonLoader {
    private static final String TAG = "JsonLoader";
    private Context context;

    public JsonLoader(Context context) {
        this.context = context;
    }

    public ReturnModel<Student> studentReturnModel() {
        //获取当前栈桢内 方法信息
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        String methodName = stackTraceElement.getMethodName();
        String className = stackTraceElement.getClassName();
        Log.i("studentReturnModel", className + "\t" + methodName);
        try {
            Method method = Class.forName(stackTraceElement.getClassName()).getMethod(methodName);
            Type returnType = method.getGenericReturnType();
            Log.i("studentReturnModel", "returnType\t" + returnType);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> void loadJsontoRuturnStudent(ICallBack<T> callBack) {
        //模拟加载数据
        String json = context.getString(R.string.jsonRuturnStudent);
        if (isSuccessRandom()) {
            callBack.onSuccess(callBack.convert(json));
        } else {
            callBack.onFailure(new IllegalArgumentException("Request random erro"));
        }
    }

    public <T> void loadJsontoRuturnStudentList(ICallBack<T> callBack) {
        //模拟加载数据
        String json = context.getString(R.string.jsonRuturnStudentList);
        if (isSuccessRandom()) {
            callBack.onSuccess(callBack.convert(json));
        } else {
            callBack.onFailure(new IllegalArgumentException("Request random erro"));
        }
    }

    public <T> T loadjsonReturnDataStudentList(TypeSupport<T> tTypeSupport) {
        String json = context.getString(R.string.jsonReturnDataStudentList);
        return new Gson().fromJson(json, tTypeSupport.getType());
    }


    private boolean isSuccessRandom() {
        //每3秒失败一次
        return Calendar.getInstance().getTimeInMillis() / 3000 != 0;
    }

}
