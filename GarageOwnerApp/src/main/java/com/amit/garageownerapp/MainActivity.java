package com.amit.garageownerapp;

import android.os.Bundle;
import android.util.Log;

import com.amit.hw2garage.myMainActivity;

public class MainActivity extends myMainActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackgroundColor(getResources().getColor(R.color.primaryBlue, getTheme()));
        setAppName("Garage Owner App");
    }
}