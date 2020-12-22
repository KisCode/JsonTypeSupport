package com.kiscode.gson.support;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kiscode.gson.support.model.PageData;
import com.kiscode.gson.support.model.ReturnModel;
import com.kiscode.gson.support.model.Student;
import com.kiscode.gson.support.support.TypeSupport;
import com.kiscode.gson.supportlib.ParameterizedTypeImp;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toObject();
//        toList();
//        toReturnModel();
//        toReturnModelList();
//        toReturnModelDataList();

    }

    public void load(View view) {
//        toReturnModelList();
        loadDataByCallBack();
    }

    private void toObject() {
        String studentJsonStr = getString(R.string.jsonStudentObject);
        Gson gson = new Gson();
        Student student = gson.fromJson(studentJsonStr, Student.class);
        Log.d(TAG, "json:\n" + studentJsonStr + "\nToString:\n" + student.toString());
    }

    private void toList() {
        String json = getString(R.string.jsonStudentList);
        Gson gson = new Gson();
        List<Student> studentList = gson.fromJson(json, new TypeToken<List<Student>>() {
        }.getType());

        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentList.toString());
    }

    private void toReturnModel() {

        String json = getString(R.string.jsonRuturnStudent);
        Gson gson = new Gson();
        /*
        ReturnModel<Student> studentReturnModel = gson.fromJson(json, new TypeToken<ReturnModel<Student>>() {
        }.getType());
        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentReturnModel.toString());
*/
        Type type = new ParameterizedTypeImp(ReturnModel.class, new Class[]{Student.class});
        ReturnModel<Student> studentReturnModel = gson.fromJson(json, type);
        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentReturnModel.toString());
    }

    private void toReturnModelList() {
        String json = getString(R.string.jsonRuturnStudentList);
        Gson gson = new Gson();
//        Type type = new TypeToken<String>().getType();
        ReturnModel<Student> studentListReturnModel = gson.fromJson(json, new TypeToken<ReturnModel<List<Student>>>() {
        }.getType());
        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentListReturnModel.toString());

        //使用ParameterizedTypeImp方式
        Type listType = new ParameterizedTypeImp(List.class, new Class[]{Student.class});
        Type type = new ParameterizedTypeImp(ReturnModel.class, new Type[]{listType});
        studentListReturnModel = gson.fromJson(json, type);
        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentListReturnModel.toString());
    }

    private void toReturnModelDataList() {
        String json = getString(R.string.jsonReturnDataStudentList);
        Gson gson = new Gson();
        ReturnModel<PageData<List<Student>>> dataStudentListReturnModel = gson.fromJson(json, new TypeToken<ReturnModel<PageData<List<Student>>>>() {
        }.getType());

        Log.d(TAG, "json:\n" + json + "\nToString:\n" + dataStudentListReturnModel.toString());
    }

    private void loadDataByCallBack() {
        JsonLoader loader = new JsonLoader(this);
/*
        ReturnModel<Student> studentReturnModel = loader.loadJsontoRuturnStudent(new ICallBack<ReturnModel<Student>>() {

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
        Log.i(TAG, "studentReturnModel:" + studentReturnModel.toString());*/

//        new TypeToken<String>(){}.getType();
//        new BaseCallBack<String>().getType();
//        new TypeSupport<String>(){}.log();

        Type type = new TypeSupport<ReturnModel<List<Student>>>() {
        }.getType();

        loader.loadJsontoRuturnStudentList(new BaseCallBack<ReturnModel<List<Student>>>() {
//            @Override
//            public void onSuccess(ReturnModel<List<Student>> studentReturnModel) {
//                Log.i(TAG, "studentReturnModelList:" + studentReturnModel.toString());
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//
//            }
        });
        ReturnModel<PageData<List<Student>>> returnModel = loader.loadjsonReturnDataStudentList(new BaseCallBack<ReturnModel<PageData<List<Student>>>>());
        Log.i(TAG, "studentReturnModel:" + returnModel.toString());
    }
}