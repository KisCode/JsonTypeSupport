package com.kiscode.gson.support;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kiscode.gson.support.callback.BaseCallBack;
import com.kiscode.gson.support.callback.ICallBack;
import com.kiscode.gson.support.model.PageData;
import com.kiscode.gson.support.model.ReturnModel;
import com.kiscode.gson.support.model.Student;
import com.kiscode.gson.support.support.TypeSupport;
import com.kiscode.gson.supportlib.ParameterizedTypeImp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericCallbackSampleActivity extends AppCompatActivity {

    private static final String TAG = "GenericCallback";
    JsonLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_callback_sample);
        loader = new JsonLoader(this);

    }

    public void executeICallBack(View view) {
        loadDataByCallBack();
    }

    public void executeBaseCallBack(View view) {
        loadDataByBaseCallBack();
    }

    public void executeTypeSupportBack(View view) {
        loadDataByTypeSupportCallBack();
    }

    private void loadDataByTypeSupportCallBack() {
        Log.i(TAG, "---------------------------loadDataByTypeSupportCallBack----------------------------------------");
        String json = getString(R.string.jsonReturnDataStudentList);
        Type type = new TypeSupport<ReturnModel<PageData<List<Student>>>>() {
        }.getType();
        Log.i(TAG, "Type = " + type);
        ReturnModel<PageData<List<Student>>> returnModel = new Gson().fromJson(json, type);
        Log.i(TAG, returnModel.toString());
    }

    private void loadDataByBaseCallBack() {
//        new TypeToken<String>(){}.getType();
//        new BaseCallBack<String>().getType();
//        new TypeSupport<String>(){}.log();
        Type type = new TypeSupport<ReturnModel<List<Student>>>() {
        }.getType();

        loader.loadJsontoRuturnStudentList(new BaseCallBack<ReturnModel<List<Student>>>() {
            @Override
            public void onSuccess(ReturnModel<List<Student>> studentReturnModel) {
                Log.i(TAG, "studentReturnModelList:" + studentReturnModel.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }


    private void loadDataByCallBack() {
        loader.loadJsontoRuturnStudent(new ICallBack<ReturnModel<Student>>() {

            @Override
            public void onSuccess(ReturnModel<Student> studentReturnModel) {
                Log.i(TAG, "studentReturnModel:" + studentReturnModel.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public ReturnModel<Student> convert(String json) {
                Class clz = this.getClass();
                Type type = clz.getGenericSuperclass();
                Log.i(TAG, "callBack class:" + clz + "\n getGenericSuperclass:" + type);
                if (type instanceof Object) {
                    Type[] types = clz.getGenericInterfaces();
                    if (types.length > 0 && types[0] instanceof ParameterizedType) {
                        Type[] params = ((ParameterizedType) types[0]).getActualTypeArguments();
                        Log.i(TAG, "开始解析:" + clz + "\t getGenericSuperclass:" + params[0]);
                        return new Gson().fromJson(json, params[0]);
                    }
                } else {
                    if (type instanceof ParameterizedTypeImp) {
                        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
                        return new Gson().fromJson(json, params[0]);
                    }
                }
                return null;
            }
        });
    }
}