package com.study.android.snooker.presenter;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application{

    private static SnookerApi snookerApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
    retrofit = new Retrofit.Builder()
            .baseUrl("http://api.snooker.org/") //Базовая часть адреса
            .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
            .build();
    snookerApi = retrofit.create(SnookerApi.class); //Создаем объект, при помощи которого будем выполнять запросы
}

    public static SnookerApi getApi() {
        return snookerApi;
    }
}
