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
    private String jsonObject = "";


    public void save() throws JSONException {
        pieces = RoomManager.getInstance().getArrayListRooms();

        jsonObject = new Gson().toJson(pieces);

        Log.i("test", jsonObject);

    }
    public void load() throws JSONException {

    }
}
