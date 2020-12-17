package com.kiscode.gson.support;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kiscode.gson.support.model.PageData;
import com.kiscode.gson.support.model.ReturnModel;
import com.kiscode.gson.support.model.Student;
import com.kiscode.gson.supportlib.GsonUtil;
import com.kiscode.gson.supportlib.ParameterizedTypeImp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

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
        toReturnModelList();
        toReturnModelDataList2();
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
        ReturnModel<Student> studentListReturnModel = gson.fromJson(json, new TypeToken<ReturnModel<List<Student>>>() {
        }.getType());
        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentListReturnModel.toString());

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

    private void toReturnModelDataList2() {
        JsonLoader loader = new JsonLoader(this);
//        ReturnModel<Student> returnModel = loader.studentReturnModel();

        loader.loadJsontoRuturnStudent(new CallBack<ReturnModel<Student>>() {
            @Override
            public void onSuccess(ReturnModel<Student> studentReturnModel) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}