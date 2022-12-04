package com.example.roomsimulator;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;

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

    public void printRooms(Context context) throws JSONException {
        Enregistrement enregistrement = new Enregistrement();
        String rooms = enregistrement.save(context, "storage.json");
        Log.i("TEST", rooms);
    }
}
