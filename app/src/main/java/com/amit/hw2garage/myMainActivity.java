package com.amit.hw2garage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amit.hw2garage.Api.GarageController;
import com.amit.hw2garage.Model.GarageModel;
import com.amit.hw2garage.Model.Session;
import com.amit.hw2garage.RoomSQL.RoomSQL;

import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class myMainActivity extends AppCompatActivity {

    private static final String TAG = "Awesome garage";
    private GarageModel mGarageModel;
    private Session mCurrentSession;
    private TextView mTitle, mTXT_Last_Session, mTXT_total_time, mTXT_Garage_Name, mTXT_Garage_Address, mTXT_Cars, mTXT_Garage_open;
    private RelativeLayout mMainLay;
    private final SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = findViewById(R.id.welcomePage_textView_title);
        mTXT_Last_Session = findViewById(R.id.TXT_Last_Session);
        mMainLay = findViewById(R.id.LAY_main);
        mTXT_total_time = findViewById(R.id.TXT_total_time);
        mTXT_Garage_Name = findViewById(R.id.TXT_Garage_Name);
        mTXT_Garage_open = findViewById(R.id.TXT_Garage_open);
        mTXT_Garage_Address = findViewById(R.id.TXT_Garage_Address);
        mTXT_Cars = findViewById(R.id.TXT_Cars);
        getGarageData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCurrentSession = new Session();
        mCurrentSession.setStart(System.currentTimeMillis());
        RoomSQL.getInstance().getLastSession(this::bindLastSession);
        RoomSQL.getInstance().getTotalSpendsTime(this::bindTotalTime);
    }

    private void bindLastSession(Session session) {
        String str = session == null ? "this is your first use" : format.format(session.getStart());
        mTXT_Last_Session.setText("Last seen: " + str);
    }

    private void bindTotalTime(long time) {
        mTXT_total_time.setText("Total usage: " + getPrettyTimeSpent(time));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCurrentSession.setEnd(System.currentTimeMillis());
        mCurrentSession.setTotal(mCurrentSession.getEnd() - mCurrentSession.getStart());
        RoomSQL.getInstance().insertSession(mCurrentSession);
        mCurrentSession = null;
    }

    private void getGarageData() {
        new GarageController().fetchGarage(garage -> {
            this.mGarageModel = garage;
            bindGarageData();
        });
    }

    private void bindGarageData() {
        mTXT_Garage_open.setText(mGarageModel.isOpen() ? "The Garage is Open" : "The Garage is Close");
        mTXT_Garage_Address.setText("Address: " + mGarageModel.getAddress());
        mTXT_Garage_Name.setText("Name: " + mGarageModel.getName());
        mTXT_Cars.setText("Cars: " + String.join(", ", mGarageModel.getCars()));
    }


    private String getPrettyTimeSpent(Long time) {
        //milliseconds
        long different = time;
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;
        long elapsedSeconds = different / secondsInMilli;
        StringBuilder sb = new StringBuilder();
        if (elapsedDays != 0)
            sb.append(elapsedDays).append("  days, ");
        if (elapsedHours != 0)
            sb.append(elapsedHours).
                    append("  hours, ");
        if (elapsedMinutes != 0)
            sb.append(elapsedMinutes).append("  minutes, ");
        if (elapsedSeconds != 0)
            sb.append(elapsedSeconds).append(" seconds");
        return sb.toString().isEmpty() ? "first use" : sb.toString();
    }

    protected void setBackgroundColor(int color) {
        mMainLay.setBackgroundColor(color);

    }

    protected void setAppName(String name) {
        mTitle.setText(name);
    }
}