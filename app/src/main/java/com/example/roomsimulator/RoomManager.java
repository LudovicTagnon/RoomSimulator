package com.example.roomsimulator;

import java.util.ArrayList;

public class RoomManager {

    private ArrayList<RoomModel> arrayListRooms = new ArrayList<>();
    private static final RoomManager instance = new RoomManager();



    public RoomManager() {
    }


    public static RoomManager getInstance(){
        return instance;
    }

    public ArrayList<RoomModel> getArrayListRooms() {
        return arrayListRooms;
    }

    public void setArrayListRooms(ArrayList<RoomModel> arrayListRooms) {
        this.arrayListRooms = arrayListRooms;
    }
}
