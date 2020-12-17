package com.kiscode.gson.support;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.kiscode.gson.support.model.ReturnModel;
import com.kiscode.gson.support.model.Student;
import com.kiscode.gson.supportlib.ParameterizedTypeImp;

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

    public <T> void loadJsontoRuturnStudent(CallBack<T> callBack) {
        Class cls = callBack.getClass();

        Type genericSuperclass = cls.getGenericSuperclass();
        Log.i(TAG, "loadJsontoRuturnStudent:" + callBack.getClass() + "\t" + genericSuperclass);


        String json = context.getString(R.string.jsonRuturnStudent);
        Gson gson = new Gson();

        Type type = new ParameterizedTypeImp(ReturnModel.class, new Class[]{Student.class});
        ReturnModel<Student> studentReturnModel = gson.fromJson(json, type);
//        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentReturnModel.toString());


        System.out.println(this.getClass().getSimpleName() + ":"
                + new Exception().getStackTrace()[0].getMethodName());

    }
}
