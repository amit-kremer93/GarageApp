package com.amit.hw2garage;

import android.app.Application;

import com.amit.hw2garage.RoomSQL.RoomSQL;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RoomSQL.initHelper(getApplicationContext());
    }
}
