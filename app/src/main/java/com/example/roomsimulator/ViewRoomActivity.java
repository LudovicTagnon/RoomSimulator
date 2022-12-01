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

    private Button buttonPorteNord;
    private Button buttonPorteEst;
    private Button buttonPorteSud;
    private Button buttonPorteOuest;
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

        //Lie les objets xml aux variables de l'activité
        imageView = findViewById(R.id.imageViewCourante);
        buttonGauche = findViewById(R.id.buttonLeft);
        buttonDroite = findViewById(R.id.buttonRight);

        //Image par défaut
        this.setImageMurNord();

        buttonGauche.setOnClickListener((v)->{
            switch (direction) {
                case "N":
                    if(arrayList.get(roomIndex).getMurOuest().getBitmap() != null) {
                        buttonPorteNord.setClickable(false);
                        buttonPorteNord.setBackgroundColor(Color.TRANSPARENT);
                        setImageMurOuest();
                        buttonPorteOuest.setClickable(true);
                        buttonPorteOuest.setBackgroundColor(Color.YELLOW);
                    }
                    break;
                case "E":
                    if(arrayList.get(roomIndex).getMurNord().getBitmap() != null) {
                        buttonPorteEst.setClickable(false);
                        buttonPorteEst.setBackgroundColor(Color.TRANSPARENT);
                        setImageMurNord();
                        buttonPorteNord.setClickable(true);
                        buttonPorteNord.setBackgroundColor(Color.YELLOW);
                    }
                    break;
                case "S":
                    if(arrayList.get(roomIndex).getMurEst().getBitmap() != null) {
                        buttonPorteSud.setClickable(false);
                        buttonPorteSud.setBackgroundColor(Color.TRANSPARENT);
                        setImageMurEst();
                        buttonPorteEst.setClickable(true);
                        buttonPorteEst.setBackgroundColor(Color.YELLOW);
                    }
                    break;
                case "O":
                    if(arrayList.get(roomIndex).getMurSud().getBitmap() != null) {
                        buttonPorteOuest.setClickable(false);
                        buttonPorteOuest.setBackgroundColor(Color.TRANSPARENT);
                        setImageMurSud();
                        buttonPorteSud.setClickable(true);
                        buttonPorteSud.setBackgroundColor(Color.YELLOW);
                    }
                    break;
            }
            setupPortes();
        });

        buttonDroite.setOnClickListener((v)->{
            switch (direction) {
                case "N":
                    if(arrayList.get(roomIndex).getMurEst().getBitmap() != null) {
                        buttonPorteNord.setClickable(false);
                        buttonPorteNord.setBackgroundColor(Color.TRANSPARENT);
                        setImageMurEst();
                        buttonPorteEst.setClickable(true);
                        buttonPorteEst.setBackgroundColor(Color.YELLOW);
                    }
                    break;
                case "E":
                    if(arrayList.get(roomIndex).getMurSud().getBitmap() != null) {
                        buttonPorteEst.setClickable(false);
                        buttonPorteEst.setBackgroundColor(Color.TRANSPARENT);
                        setImageMurSud();
                        buttonPorteSud.setClickable(true);
                        buttonPorteSud.setBackgroundColor(Color.YELLOW);
                    }
                    break;
                case "S":
                    if(arrayList.get(roomIndex).getMurOuest().getBitmap() != null) {
                        buttonPorteSud.setClickable(false);
                        buttonPorteSud.setBackgroundColor(Color.TRANSPARENT);
                        setImageMurOuest();
                        buttonPorteOuest.setClickable(true);
                        buttonPorteOuest.setBackgroundColor(Color.YELLOW);
                    }
                    break;
                case "O":
                    if(arrayList.get(roomIndex).getMurNord().getBitmap() != null) {
                        buttonPorteOuest.setClickable(false);
                        buttonPorteOuest.setBackgroundColor(Color.TRANSPARENT);
                        setImageMurNord();
                        buttonPorteNord.setClickable(true);
                        buttonPorteNord.setBackgroundColor(Color.YELLOW);
                    }
                    break;
            }
            setupPortes();
        });

        setupPortes();

    }

    public void setImageMurNord(){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurNord().getBitmap());
            this.direction = "N";
    }

    public void setImageMurEst(){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurEst().getBitmap());
            this.direction = "E";
    }

    public void setImageMurSud(){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurSud().getBitmap());
            this.direction = "S";
    }

    public void setImageMurOuest(){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurOuest().getBitmap());
            this.direction = "O";
    }

    public void setupPortes(){

        buttonPorteNord = new Button(this);
        buttonPorteEst = new Button(this);
        buttonPorteSud = new Button(this);
        buttonPorteOuest = new Button(this);

        switch (direction) {
            case "N":
                if(arrayList.get(roomIndex).getMurNord().getPortes().size() != 0) {
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.width = arrayList.get(roomIndex).getMurNord().getPortes().get(0).width();
                    params.height = arrayList.get(roomIndex).getMurNord().getPortes().get(0).height();
                    buttonPorteNord.setLayoutParams(params);
                    buttonPorteNord.setX(arrayList.get(roomIndex).getMurNord().getPortes().get(0).left);
                    buttonPorteNord.setY(arrayList.get(roomIndex).getMurNord().getPortes().get(0).top);
                    buttonPorteNord.setBackgroundColor(Color.YELLOW);
                    Log.i("TEST", "X=" + buttonPorteNord.getX() + "Y=" + buttonPorteNord.getY() + "W=" + buttonPorteNord.getWidth() + "H=" + buttonPorteNord.getHeight());
                    ViewRoomActivity.this.addContentView(buttonPorteNord, buttonPorteNord.getLayoutParams());
                }
                break;
            case "E":
                if(arrayList.get(roomIndex).getMurEst().getPortes().size() != 0) {
                    ViewGroup.LayoutParams paramsE = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    paramsE.width = arrayList.get(roomIndex).getMurEst().getPortes().get(0).width();
                    paramsE.height = arrayList.get(roomIndex).getMurEst().getPortes().get(0).height();
                    buttonPorteEst.setLayoutParams(paramsE);
                    buttonPorteEst.setX(arrayList.get(roomIndex).getMurEst().getPortes().get(0).left);
                    buttonPorteEst.setY(arrayList.get(roomIndex).getMurEst().getPortes().get(0).top);
                    buttonPorteEst.setBackgroundColor(Color.YELLOW);
                    Log.i("TEST", "X=" + buttonPorteEst.getX() + "Y=" + buttonPorteEst.getY() + "W=" + buttonPorteEst.getWidth() + "H=" + buttonPorteEst.getHeight());
                    ViewRoomActivity.this.addContentView(buttonPorteEst, buttonPorteEst.getLayoutParams());
                }
                break;
            case "S":
                if(arrayList.get(roomIndex).getMurEst().getPortes().size() != 0) {
                    ViewGroup.LayoutParams paramsS = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    paramsS.width = arrayList.get(roomIndex).getMurSud().getPortes().get(0).width();
                    paramsS.height = arrayList.get(roomIndex).getMurSud().getPortes().get(0).height();
                    buttonPorteSud.setLayoutParams(paramsS);
                    buttonPorteSud.setX(arrayList.get(roomIndex).getMurSud().getPortes().get(0).left);
                    buttonPorteSud.setY(arrayList.get(roomIndex).getMurSud().getPortes().get(0).top);
                    buttonPorteSud.setBackgroundColor(Color.YELLOW);
                    Log.i("TEST", "X=" + buttonPorteSud.getX() + "Y=" + buttonPorteSud.getY() + "W=" + buttonPorteSud.getWidth() + "H=" + buttonPorteSud.getHeight());
                    ViewRoomActivity.this.addContentView(buttonPorteSud, buttonPorteSud.getLayoutParams());
                }
                break;
            case "O":
                if(arrayList.get(roomIndex).getMurOuest().getPortes().size() != 0) {
                    ViewGroup.LayoutParams paramsO = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    paramsO.width = arrayList.get(roomIndex).getMurOuest().getPortes().get(0).width();
                    paramsO.height = arrayList.get(roomIndex).getMurOuest().getPortes().get(0).height();
                    buttonPorteOuest.setLayoutParams(paramsO);
                    buttonPorteOuest.setX(arrayList.get(roomIndex).getMurOuest().getPortes().get(0).left);
                    buttonPorteOuest.setY(arrayList.get(roomIndex).getMurOuest().getPortes().get(0).top);
                    buttonPorteOuest.setBackgroundColor(Color.YELLOW);
                    Log.i("TEST", "X=" + buttonPorteOuest.getX() + "Y=" + buttonPorteOuest.getY() + "W=" + buttonPorteOuest.getWidth() + "H=" + buttonPorteOuest.getHeight());
                    ViewRoomActivity.this.addContentView(buttonPorteOuest, buttonPorteOuest.getLayoutParams());
                }
                break;
        }

        buttonPorteNord.setOnClickListener((v)->{
            Toast.makeText(ViewRoomActivity.this, "Porte", Toast.LENGTH_SHORT).show();
        });
    }

}