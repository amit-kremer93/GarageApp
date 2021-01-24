package com.amit.hw2garage.RoomSQL;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.amit.hw2garage.Model.Session;

@Database(entities = {Session.class}, version = 1)
public abstract class myDataBase extends RoomDatabase {
    public abstract SessionDao sessionDao();
}
