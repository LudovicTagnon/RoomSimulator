package com.example.roomsimulator;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

public class AddDoorActivity extends AppCompatActivity {

    private int roomIndex;
    private String direction;
    private ImageView imageView;
    private ArrayList<RoomModel> arrayList;
    protected Rect rect;
    protected CanvasView canvasView;
    protected SurfaceView surfaceView;

    private Button buttonConfirmer;


    //======================================================


    private RecyclerView recyclerView;

    private MainAdapter adapter;

    private RoomModel selectedRoom;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_door);


        //Récupère l'index de la pièce sélectionnée dans la recyclerview
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            roomIndex = Integer.parseInt(extras.getString("roomIndex"));
            direction = extras.getString("direction");
        }

        //Associe l'imageView xml avec celle du code
        imageView = findViewById(R.id.imageViewAddDoor);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_rooms);

        //Récupère les rooms
        arrayList = RoomManager.getInstance().getArrayListRooms();

        getRoomList();


        if(direction.equals("N")){
            setNord();
        }else if(direction.equals("E")){
            setEst();
        }else if(direction.equals("S")){
            setSud();
        }else if(direction.equals("O")){
            setOuest();
        }


        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.setZOrderOnTop(true);
        surfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);

        buttonConfirmer = findViewById(R.id.buttonConfirmerPorte);


        imageView.setOnTouchListener(
                new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getPointerCount() == 2) {

                            rect = new Rect((int) event.getX(0), (int) event.getY(0), (int) event.getX(1), (int) event.getY(1));
                            rect.sort();

                            canvasView = new CanvasView(AddDoorActivity.this, surfaceView, rect);
                            AddDoorActivity.this.addContentView(canvasView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                            canvasView.reDraw();

                        }

                        return true;
                    }
                }
        );

        //============================================================================================




        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(AddDoorActivity.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                if(!adapter.isChecked(position)){
                    adapter.setChecked(position);
                }
                selectedRoom = arrayList.get(position);
            }
            @Override
            public void onLongItemClick(View view, int position) {}
        }));


        buttonConfirmer.setOnClickListener((b)->{
            //Lier à la prochaine pièce
            if (rect != null && selectedRoom != null) {
                if(selectedRoom.getMurNord().getBitmap() != null)
                {
                    DoorModel d = new DoorModel(rect, selectedRoom.getName());
                    switch (direction) {
                        case "N":
                            arrayList.get(roomIndex).getMurNord().getPortes().add(d);
                            break;
                        case "E":
                            arrayList.get(roomIndex).getMurEst().getPortes().add(d);
                            break;
                        case "S":
                            arrayList.get(roomIndex).getMurSud().getPortes().add(d);
                            break;
                        case "O":
                            arrayList.get(roomIndex).getMurOuest().getPortes().add(d);
                            break;
                    }
                    Toast.makeText(this, "Porte ajoutée!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Erreur: Il faut ajouter les photos du mur avant d'ajouter des portes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton trashButton = findViewById(R.id.imageButtonTrash);
        trashButton.setOnClickListener((v)->{
            switch (direction) {
                case "N":
                    this.arrayList.get(roomIndex).getMurNord().setPortes(new ArrayList<DoorModel>());
                    break;
                case "E":
                    this.arrayList.get(roomIndex).getMurEst().setPortes(new ArrayList<DoorModel>());
                    break;
                case "S":
                    this.arrayList.get(roomIndex).getMurSud().setPortes(new ArrayList<DoorModel>());
                    break;
                case "O":
                    this.arrayList.get(roomIndex).getMurOuest().setPortes(new ArrayList<DoorModel>());
                    break;
            }
            finish();
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        arrayList = RoomManager.getInstance().getArrayListRooms();
    }

    public void setNord(){
        if(arrayList.get(roomIndex).getMurNord().getBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurNord().getBitmap());
            this.direction = "N";
        }
    }

    public void setEst(){
        if(arrayList.get(roomIndex).getMurEst().getBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurEst().getBitmap());
            this.direction = "E";
        }
    }

    public void setSud(){
        if(arrayList.get(roomIndex).getMurSud().getBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurSud().getBitmap());
            this.direction = "S";
        }
    }

    public void setOuest(){
        if(arrayList.get(roomIndex).getMurOuest().getBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurOuest().getBitmap());
            this.direction = "O";
        }
    }

    private void getRoomList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
        /*try {
            RoomManager.getInstance().printRooms(getBaseContext());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        for(RoomModel room : arrayList){
            Log.i("testRoomModel", room.toString());
        }*/

    }

}