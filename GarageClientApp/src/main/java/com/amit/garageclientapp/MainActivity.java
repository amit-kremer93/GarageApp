package com.amit.garageclientapp;
import android.os.Bundle;
import com.amit.hw2garage.myMainActivity;

public class MainActivity extends myMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackgroundColor(getResources().getColor(R.color.greenSuccess, getTheme()));
        setAppName("Garage Client App");
    }
}