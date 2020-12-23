package com.kiscode.gson.support;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kiscode.gson.support.model.PageData;
import com.kiscode.gson.support.model.ReturnModel;
import com.kiscode.gson.support.model.Student;
import com.kiscode.gson.supportlib.ParameterizedTypeImp;

import java.lang.reflect.Type;
import java.util.List;

public class GsonSampleActivity extends AppCompatActivity {

    private static final String TAG = "GsonSampleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_sample);
    }

    public void startParser(View view) {
        toObject();
        Log.d(TAG, "-------------------------------------------------------------------------");
        toList();
        Log.d(TAG, "-------------------------------------------------------------------------");
        toReturnModel();
        Log.d(TAG, "-------------------------------------------------------------------------");
        toReturnModelList();
        Log.d(TAG, "-------------------------------------------------------------------------");
        toReturnModelDataList();
        Log.d(TAG, "-------------------------------------------------------------------------");
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
}