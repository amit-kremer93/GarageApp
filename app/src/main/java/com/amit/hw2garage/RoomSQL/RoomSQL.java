package com.amit.hw2garage.RoomSQL;

import android.content.Context;

import androidx.room.Room;

import com.amit.hw2garage.Model.Session;
import com.paz.taskrunnerlib.task_runner.RunnerCallback;
import com.paz.taskrunnerlib.task_runner.TaskRunner;

public class RoomSQL {
    private static myDataBase mDatabase;
    private static RoomSQL instance;

    private RoomSQL(Context context) {

        String pName = context.getApplicationContext().getPackageName();
        mDatabase = Room.databaseBuilder(context.getApplicationContext(), myDataBase.class, pName + "-GarageDB.db")
                .build();
    }

    public static RoomSQL getInstance() {
        return instance;
    }

    public static RoomSQL initHelper(Context context) {
        if (instance == null) {
            instance = new RoomSQL(context);
        }

        return instance;
    }


    public interface CallBackGetLast {
        void dataReady(Session session);
    }

    public void getLastSession(CallBackGetLast callBackGetLast) {
        TaskRunner<Session> taskRunner = new TaskRunner<>();
        taskRunner.executeAsync(new RunnerCallback<Session>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public Session call() throws Exception {
                return mDatabase.sessionDao().getLastSession();

            }

            @Override
            public void onPostExecute(Session result) {
                callBackGetLast.dataReady(result);

            }
        });


    }

    public interface CallBackGetTotalSpentTime {
        void dataReady(long time);
    }

    public void getTotalSpendsTime(CallBackGetTotalSpentTime callback) {
        TaskRunner<Long> taskRunner = new TaskRunner<>();
        taskRunner.executeAsync(new RunnerCallback<Long>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public Long call() throws Exception {
                return mDatabase.sessionDao().totalSpendsTime();
            }

            @Override
            public void onPostExecute(Long result) {
                callback.dataReady(result);

            }
        });

    }

    public void insertSession(Session session) {
        new Thread(() -> mDatabase.sessionDao().insert(session)).start();
    }
}
