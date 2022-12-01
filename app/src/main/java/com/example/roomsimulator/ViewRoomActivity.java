package com.example.roomsimulator;

import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewRoomActivity extends AppCompatActivity {

    private ArrayList<RoomModel> arrayList;

    private RoomModel currentRoom;
    private int roomIndex;

    private ImageView imageView;

    private Button buttonGauche;
    private Button buttonDroite;

    private String direction = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room);

        this.direction = "N";

        //Récupère l'index de la pièce sélectionnée dans la recyclerview
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("roomSelected");
            roomIndex = Integer.parseInt(value);
        }

        //Récupère les rooms
        arrayList = RoomManager.getInstance().getArrayListRooms();

        //Lie l'imageview xml à la variable imageview du room manager
        imageView = findViewById(R.id.imageViewCourante);

        this.setNord();

        buttonGauche = findViewById(R.id.buttonLeft);
        buttonDroite = findViewById(R.id.buttonRight);

        buttonGauche.setOnClickListener((v)->{
            if(direction.equals("N")){
                setOuest();
            }else if(direction.equals("E")){
                setNord();
            }else if(direction.equals("S")){
                setEst();
            }else if(direction.equals("O")){
                setSud();
            }
        });

        buttonDroite.setOnClickListener((v)->{
            if(direction.equals("N")){
                setEst();
            }else if(direction.equals("E")){
                setSud();
            }else if(direction.equals("S")){
                setOuest();
            }else if(direction.equals("O")){
                setNord();
            }
        });

    }

    public void setNord(){
        if(arrayList.get(roomIndex).getImageNordBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getImageNordBitmap());
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 1000;
            this.direction = "N";
        }
    }

    public void setEst(){
        if(arrayList.get(roomIndex).getImageEstBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getImageEstBitmap());
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 1000;
            this.direction = "E";
        }
    }

    public void setSud(){
        if(arrayList.get(roomIndex).getImageSudBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getImageSudBitmap());
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 1000;
            this.direction = "S";
        }
    }

    public void setOuest(){
        if(arrayList.get(roomIndex).getImageOuestBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getImageOuestBitmap());
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 1000;
            this.direction = "O";
        }
    }

}