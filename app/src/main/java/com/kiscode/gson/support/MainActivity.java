package com.kiscode.gson.support;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kiscode.gson.support.model.PageData;
import com.kiscode.gson.support.model.ReturnModel;
import com.kiscode.gson.support.model.Student;
import com.kiscode.gson.support.support.TypeSupport;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void load(View view) {
//        toReturnModelList();
//        loadDataByCallBack();
    }

    public void openGsonSample(View view) {
        startActivity(new Intent(this, GsonSampleActivity.class));
    }


    public void openGenericeSample(View view) {
        startActivity(new Intent(this, GenericCallbackSampleActivity.class));
    }
}