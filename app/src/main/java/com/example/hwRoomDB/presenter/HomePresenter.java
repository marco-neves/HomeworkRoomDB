package com.example.hwRoomDB.presenter;

import androidx.room.Room;

import com.example.hwRoomDB.database.HWEntity;
import com.example.hwRoomDB.database.HWDB;
import com.example.hwRoomDB.presenter.Contract.HomeworkPresenter;
import com.example.hwRoomDB.view.MainActivity;

import java.util.List;

public class HomePresenter implements HomeworkPresenter {


    private Contract.HomeworkView hwView;
    private HWDB hwDB;
    private List<HWEntity> hws;

    public HomePresenter(Contract.HomeworkView hwView) {
        this.hwView = hwView;
        try{
            hwDB = Room.databaseBuilder(((MainActivity) hwView).getApplicationContext(),
                    HWDB.class,
                    "room.db").allowMainThreadQueries().build();
        }catch (Exception e){
            hwView.displayError("Failed to create database.");
        }
    }

    @Override
    public void getHomeworks() {
        hws = hwDB.homeworkDAO().getAllHomeworks();
        if(hws.isEmpty()){
            hwView.homeworksEmpty("Nothing on file...");
        }else {
            hwView.homeworksEmpty("Homeworks");
            hwView.displayHomeworks(hws);
        }
    }

    @Override
    public void insertHomework(HWEntity hw) {
        try{
            hwDB.homeworkDAO().insertNewHomework(hw);
        }catch(Exception e){
            hwView.displayError("Failed to insert "+hw.getHomeworkTitle());
        }
    }



    @Override
    public void modifyHomework(HWEntity hw) {
        try {
            hwDB.homeworkDAO().updateEntity(hw);
        }catch (Exception e){
            hwView.displayError("Failed to update "+hw.getHomeworkTitle());
        }
    }
}