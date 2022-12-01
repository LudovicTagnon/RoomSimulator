package com.example.roomsimulator;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Enregistrement {

    private ArrayList<RoomModel> pieces;
    private ArrayList<RoomModel> piecesCopy;
    private String jsonObject = "";


    public void save() throws JSONException {
        pieces = RoomManager.getInstance().getArrayListRooms();
        piecesCopy = new ArrayList<>();

        for (RoomModel room: pieces) {
            RoomModel pieceCopy = new RoomModel(room.getName());
            for (int i = 0; i < 4; i++) {
                if(i==0){
                    WallModel wallcopy = new WallModel("N");
                    for (DoorModel door: room.getMurNord().getPortes()) {
                        DoorModel doorCopy = new DoorModel(door.getPosition(), door.getNextRoom());
                        wallcopy.getPortes().add(doorCopy);
                    }
                    pieceCopy.setMurNord(wallcopy);
                }else if(i==1){
                    WallModel wallcopy = new WallModel("E");
                    for (DoorModel door: room.getMurEst().getPortes()) {
                        DoorModel doorCopy = new DoorModel(door.getPosition(), door.getNextRoom());
                        wallcopy.getPortes().add(doorCopy);
                    }
                    pieceCopy.setMurEst(wallcopy);
                }else if(i==2){
                    WallModel wallcopy = new WallModel("S");
                    for (DoorModel door: room.getMurSud().getPortes()) {
                        DoorModel doorCopy = new DoorModel(door.getPosition(), door.getNextRoom());
                        wallcopy.getPortes().add(doorCopy);
                    }
                    pieceCopy.setMurSud(wallcopy);
                }else {
                    WallModel wallcopy = new WallModel("O");
                    for (DoorModel door: room.getMurOuest().getPortes()) {
                        DoorModel doorCopy = new DoorModel(door.getPosition(), door.getNextRoom());
                        wallcopy.getPortes().add(doorCopy);
                    }
                    pieceCopy.setMurOuest(wallcopy);
                }
            }
            piecesCopy.add(pieceCopy);
        }


        jsonObject = new Gson().toJson(piecesCopy);

        Log.i("test", jsonObject);

    }
    public void load() throws JSONException {

    }
}
