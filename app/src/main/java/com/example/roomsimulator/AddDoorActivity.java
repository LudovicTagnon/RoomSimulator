package com.example.roomsimulator;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class AddDoorActivity extends AppCompatActivity {

    private int roomIndex;
    private String direction;
    private ImageView imageView;
    private ArrayList<RoomModel> arrayList;
    protected Rect rect;
    protected CanvasView canvasView;
    protected SurfaceView surfaceView;

    private Button buttonConfirmer;

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

        //Récupère les rooms
        arrayList = RoomManager.getInstance().getArrayListRooms();

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

        buttonConfirmer.setOnClickListener((b)->{
                switch (direction) {
                    case "N":
                        arrayList.get(roomIndex).getMurNord().getPortes().add(rect);
                        break;
                    case "E":
                        arrayList.get(roomIndex).getMurEst().getPortes().add(rect);
                        break;
                    case "S":
                        arrayList.get(roomIndex).getMurSud().getPortes().add(rect);
                        break;
                    case "O":
                        arrayList.get(roomIndex).getMurOuest().getPortes().add(rect);
                        break;
                }
        });


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



}