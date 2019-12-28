package com.example.hwRoomDB.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.hwRoomDB.util.Constants;

@Entity(tableName = "homeworks")
public class HWEntity extends Constants implements Comparable<HWEntity>{

    @PrimaryKey(autoGenerate = true)
    private int hwID;

    @ColumnInfo(name = "homework_description")
    private String hwDescription;

    @ColumnInfo(name = "homework_date")
    private double hwDay;

    @ColumnInfo(name = "hwDone")
    private boolean hwDone;

    public HWEntity(int hwID, String hwDescription, double hwDay, boolean hwDone) {
        this.hwID = hwID;
        this.hwDescription = hwDescription;
        this.hwDay = hwDay;
        this.hwDone = hwDone;
    }
    @Ignore
    public HWEntity(String hwDescription, double hwDay, boolean hwDone) {
        this.hwDescription = hwDescription;
        this.hwDay = hwDay;
        this.hwDone = hwDone;
    }

    public String toString(){
        String toReturn = (hwDay + ": "
                + hwDescription + " is done:"
                + hwDone);
        return toReturn;
    }

    public String dateToString(){
        int week = (int)Math.round(hwDay);
        int day = (int)(hwDay *10%10);
        String weekDay;
        switch (day){
            case 0:
                weekDay = Constants.MONDAY;
                break;
            case 1:
                weekDay = Constants.TUESDAY;
                break;
            case 2:
                weekDay = Constants.WEDNESDAY;
                break;
            case 3:
                weekDay = Constants.THURSDAY;
                break;
            case 4:
                weekDay = Constants.FRIDAY;
                break;
            default:
                weekDay = "";
                break;
        }
        return "Week "+week+", "+weekDay;
    }

    public String completedToString(){
        if(hwDone){
            return "Done";
        }else{
            return "Not Done";
        }
    }

    public double getHwDay() {
        return hwDay;
    }

    public void setHwDay(double hwDay) {
        this.hwDay = hwDay;
    }

    public boolean isHwDone() {
        return hwDone;
    }

    public void setHwDone(boolean hwDone) {
        this.hwDone = hwDone;
    }

    public int getHwID() {
        return hwID;
    }

    public void setHwID(int hwID) {
        this.hwID = hwID;
    }

    public String getHomeworkTitle() {
        return hwDescription;
    }

    public String getHwDescription() {
        return hwDescription;
    }

    public void setHwDescription(String hwDescription) {
        this.hwDescription = hwDescription;
    }

    @Override
    public int compareTo(HWEntity o) {
        return ((Double) hwDay).compareTo(o.hwDay);
    }
}
