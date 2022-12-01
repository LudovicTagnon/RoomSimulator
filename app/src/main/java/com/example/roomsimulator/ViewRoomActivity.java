package com.example.roomsimulator;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
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

    private Button buttonPorte;

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
            switch (direction) {
                case "N":
                    setOuest();
                    break;
                case "E":
                    setNord();
                    break;
                case "S":
                    setEst();
                    break;
                case "O":
                    setSud();
                    break;
            }
        });

        buttonDroite.setOnClickListener((v)->{
            switch (direction) {
                case "N":
                    setEst();
                    break;
                case "E":
                    setSud();
                    break;
                case "S":
                    setOuest();
                    break;
                case "O":
                    setNord();
                    break;
            }
        });

        buttonPorte = new Button(this);
        switch (direction) {
            case "N":
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.width = arrayList.get(roomIndex).getMurNord().getPortes().get(0).width();
                params.height = arrayList.get(roomIndex).getMurNord().getPortes().get(0).height();
                buttonPorte.setLayoutParams(params);
                buttonPorte.setX(arrayList.get(roomIndex).getMurNord().getPortes().get(0).left);
                buttonPorte.setY(arrayList.get(roomIndex).getMurNord().getPortes().get(0).top);
                buttonPorte.setBackgroundColor(Color.YELLOW);
                Log.i("TEST", "X=" + buttonPorte.getX() + "Y=" + buttonPorte.getY() + "W=" + buttonPorte.getWidth() + "H=" + buttonPorte.getHeight());
                ViewRoomActivity.this.addContentView(buttonPorte, buttonPorte.getLayoutParams());
                break;
            case "E":
                setSud();
                break;
            case "S":
                setOuest();
                break;
            case "O":
                setNord();
                break;
        }

        buttonPorte.setOnClickListener((v)->{
            Toast.makeText(ViewRoomActivity.this, "Porte", Toast.LENGTH_SHORT).show();
        });

    }

    public void setNord(){
        if(arrayList.get(roomIndex).getMurNord().getBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurNord().getBitmap());
            imageView.getLayoutParams().height = 1080;
            imageView.getLayoutParams().width = 1080;
            this.direction = "N";
        }
    }

    public void setEst(){
        if(arrayList.get(roomIndex).getMurEst().getBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurEst().getBitmap());
            imageView.getLayoutParams().height = 1080;
            imageView.getLayoutParams().width = 1080;
            this.direction = "E";
        }
    }

    public void setSud(){
        if(arrayList.get(roomIndex).getMurSud().getBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurSud().getBitmap());
            imageView.getLayoutParams().height = 1080;
            imageView.getLayoutParams().width = 1080;
            this.direction = "S";
        }
    }

    public void setOuest(){
        if(arrayList.get(roomIndex).getMurOuest().getBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurOuest().getBitmap());
            imageView.getLayoutParams().height = 1080;
            imageView.getLayoutParams().width = 1080;
            this.direction = "O";
        }
    }

}