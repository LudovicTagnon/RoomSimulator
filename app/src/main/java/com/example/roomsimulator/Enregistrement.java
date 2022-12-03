package com.example.roomsimulator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;


public class Enregistrement {

    private ArrayList<RoomModel> pieces;
    private ArrayList<RoomModel> piecesCopy;
    private String jsonObject = "";


    public void save(Context context, String fileName) throws JSONException {



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

        String jsonString = jsonObject;
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
        }
        catch (IOException fileNotFound) {
            Log.i("ERREUR", "JSON FILE NOT FOUND");
        }

    }
    public void load(Context context, String fileName) throws JSONException, IOException {

        FileInputStream fis = context.openFileInput(fileName);

        if(fis != null){
            String jsonString = getFileContent(fis);
            Log.i("TEST", jsonString);

            Type collectionType = new TypeToken<ArrayList<RoomModel>>(){}.getType();
            ArrayList<RoomModel> arrayListTmp = new Gson().fromJson(jsonString, collectionType);

            RoomManager.getInstance().setArrayListRooms(arrayListTmp);


        }

        loadImages(context);

        Log.i("TEST", "LOAD SUCCESSFUL");
    }


    public String getFileContent(FileInputStream fis){
        StringBuilder sb = new StringBuilder();
        Reader r = null;
        try {
            r = new InputStreamReader(fis, "UTF-8");
            int ch = r.read();
            while (ch >= 0){
                sb.append((char) ch);
                ch = r.read();
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }



    public void loadImages(Context context){

        ArrayList<RoomModel> pieces = RoomManager.getInstance().getArrayListRooms();

        for (RoomModel r: pieces) {
            String file = r.getName() + "_" + "N" + "_" +".data";
            if (fileExist(context, file)){
                try {
                    FileInputStream fis = context.openFileInput(file);
                    Bitmap bm = BitmapFactory.decodeStream(fis);
                    r.getMurNord().setBitmap(bm);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
            file = r.getName() + "_" + "E" + "_" +".data";
            if (fileExist(context, file)){
                try {
                    FileInputStream fis = context.openFileInput(file);
                    Bitmap bm = BitmapFactory.decodeStream(fis);
                    r.getMurEst().setBitmap(bm);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
            file = r.getName() + "_" + "S" + "_" +".data";
            if (fileExist(context, file)){
                try {
                    FileInputStream fis = context.openFileInput(file);
                    Bitmap bm = BitmapFactory.decodeStream(fis);
                    r.getMurSud().setBitmap(bm);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
            file = r.getName() + "_" + "O" + "_" +".data";
            if (fileExist(context, file)){
                try {
                    FileInputStream fis = context.openFileInput(file);
                    Bitmap bm = BitmapFactory.decodeStream(fis);
                    r.getMurOuest().setBitmap(bm);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean fileExist(Context context, String fname){
        File file = context.getFileStreamPath(fname);
        return file.exists();
    }
}
