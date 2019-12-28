package com.example.hwRoomDB.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HWDAO {

    @Query("SELECT * FROM homeworks ORDER BY homework_date")
    List<HWEntity> getAllHomeworks();

    @Insert
    void insertNewHomework(HWEntity homework);

    @Delete
    void deleteHomework(HWEntity homework);

    @Update
    void updateEntity(HWEntity homework);

    @Query("SELECT * FROM homeworks")
    double getTotalCost();



}
