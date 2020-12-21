package com.kiscode.gson.support;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kiscode.gson.support.model.PageData;
import com.kiscode.gson.support.model.ReturnModel;
import com.kiscode.gson.support.model.Student;
import com.kiscode.gson.supportlib.ParameterizedTypeImp;

import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Type;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public static final String TAG = "ExampleUnitTest";

    @Mock
    Context context;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void toObject() {
        //模拟方法调用的返回值，隔离对Android系统的依赖
        when(context.getString(R.string.app_name)).thenReturn(TAG);
        assertThat(context.getString(R.string.app_name), is(TAG));


        String studentJsonStr = context.getString(R.string.jsonStudentObject);
        Gson gson = new Gson();
        Student student = gson.fromJson(studentJsonStr, Student.class);
        Log.d(TAG, "json:\n" + studentJsonStr + "\nToString:\n" + student.toString());

        assertEquals(gson.fromJson(studentJsonStr, Student.class).getClass(),Student.class);
    }
/*
    @Test
    public void toList() {
        String json = context.getString(R.string.jsonStudentList);
        Gson gson = new Gson();
        List<Student> studentList = gson.fromJson(json, new TypeToken<List<Student>>() {
        }.getType());

        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentList.toString());
    }

    @Test
    public void toReturnModel() {

        String json = context.getString(R.string.jsonRuturnStudent);
        Gson gson = new Gson();
        *//*
        ReturnModel<Student> studentReturnModel = gson.fromJson(json, new TypeToken<ReturnModel<Student>>() {
        }.getType());
        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentReturnModel.toString());
*//*
        Type type = new ParameterizedTypeImp(ReturnModel.class, new Class[]{Student.class});
        ReturnModel<Student> studentReturnModel = gson.fromJson(json, type);
        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentReturnModel.toString());
    }

    @Test
    public void toReturnModelList() {
        String json = context.getString(R.string.jsonRuturnStudentList);
        Gson gson = new Gson();
        ReturnModel<Student> studentListReturnModel = gson.fromJson(json, new TypeToken<ReturnModel<List<Student>>>() {
        }.getType());
        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentListReturnModel.toString());

        Type listType = new ParameterizedTypeImp(List.class, new Class[]{Student.class});
        Type type = new ParameterizedTypeImp(ReturnModel.class, new Type[]{listType});
        studentListReturnModel = gson.fromJson(json, type);
        Log.d(TAG, "json:\n" + json + "\nToString:\n" + studentListReturnModel.toString());
    }

    @Test
    public void toReturnModelDataList() {
        String json = context.getString(R.string.jsonReturnDataStudentList);
        Gson gson = new Gson();
        ReturnModel<PageData<List<Student>>> dataStudentListReturnModel = gson.fromJson(json, new TypeToken<ReturnModel<PageData<List<Student>>>>() {
        }.getType());

        Log.d(TAG, "json:\n" + json + "\nToString:\n" + dataStudentListReturnModel.toString());
    }*/
}