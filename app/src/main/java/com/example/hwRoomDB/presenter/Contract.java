package com.example.hwRoomDB.presenter;

import com.example.hwRoomDB.database.HWEntity;

import java.util.List;

public interface Contract {

    interface HomeworkPresenter{
//      will be used to fetch homeworks
        void getHomeworks();
        void insertHomework(HWEntity homework);
        void modifyHomework(HWEntity homework);
    }


    interface HomeworkView{
        void displayHomeworks(List<HWEntity> homeworks);
        void homeworksEmpty(String text);
        void displayError(String error);
    }

}
