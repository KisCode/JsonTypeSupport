package com.kiscode.gson.support;


import android.content.Context;
import android.util.Log;

import com.kiscode.gson.support.model.ReturnModel;
import com.kiscode.gson.support.model.Student;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

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

    public <T> T loadJsontoRuturnStudent(ICallBack<T> callBack) {
        Class cls = callBack.getClass();
        Type type = cls.getGenericSuperclass();
        Log.i(TAG, "callBack class:" + callBack.getClass() + "\n getGenericSuperclass:" + type);
        String json = context.getString(R.string.jsonRuturnStudent);
        return callBack.convert(json);
    }

    public <T> T loadJsontoRuturnStudentList(ICallBack<T> callBack) {
        Class cls = callBack.getClass();
        Type type = cls.getGenericSuperclass();
        Log.i(TAG, "callBack class:" + callBack.getClass() + "\n getGenericSuperclass:" + type);
        String json = context.getString(R.string.jsonRuturnStudentList);
        return callBack.convert(json);
    }
}
