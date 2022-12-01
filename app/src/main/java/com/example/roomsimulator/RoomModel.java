package com.example.roomsimulator;

import android.widget.ImageView;

import java.util.ArrayList;

public class RoomModel {
    private String name;

    private ArrayList<ImageView> arrayListMurs = new ArrayList<>();

    public RoomModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<ImageView> getArrayListMurs() {
        return arrayListMurs;
    }

}
