package com.example.hwRoomDB.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {HWEntity.class}, exportSchema = false)
public abstract class HWDB extends RoomDatabase {

    public abstract HWDAO homeworkDAO();

}
