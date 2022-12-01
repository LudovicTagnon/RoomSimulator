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

    ArrayList<Button> buttonPorteNord = new ArrayList<>();
    ArrayList<Button> buttonPorteEst = new ArrayList<>();
    ArrayList<Button> buttonPorteSud = new ArrayList<>();
    ArrayList<Button> buttonPorteOuest = new ArrayList<>();
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
                        for (Button b: buttonPorteNord) {
                            b.setClickable(false); //TODO
                            b.setBackgroundColor(Color.TRANSPARENT);
                        }

                        setImageMurOuest();
                        for (Button b: buttonPorteOuest) {
                            b.setClickable(true);
                            b.setBackgroundColor(Color.YELLOW);
                        }

                    }
                    break;
                case "E":
                    if(arrayList.get(roomIndex).getMurNord().getBitmap() != null) {
                        for (Button b: buttonPorteEst) {
                            b.setClickable(false);
                            b.setBackgroundColor(Color.TRANSPARENT);
                        }
                        setImageMurNord();
                        for (Button b: buttonPorteNord) {
                            b.setClickable(true);
                            b.setBackgroundColor(Color.YELLOW);
                        }
                    }
                    break;
                case "S":
                    if(arrayList.get(roomIndex).getMurEst().getBitmap() != null) {
                        for (Button b: buttonPorteSud) {
                            b.setClickable(false);
                            b.setBackgroundColor(Color.TRANSPARENT);
                        }
                        setImageMurEst();
                        for (Button b: buttonPorteEst) {
                            b.setClickable(true);
                            b.setBackgroundColor(Color.YELLOW);
                        }
                    }
                    break;
                case "O":
                    if(arrayList.get(roomIndex).getMurSud().getBitmap() != null) {
                        for (Button b: buttonPorteOuest) {
                            b.setClickable(false);
                            b.setBackgroundColor(Color.TRANSPARENT);
                        }
                        setImageMurSud();
                        for (Button b: buttonPorteSud) {
                            b.setClickable(true);
                            b.setBackgroundColor(Color.YELLOW);
                        }
                    }
                    break;
            }
            setupPortes();
        });

        buttonDroite.setOnClickListener((v)->{
            switch (direction) {
                case "N":
                    if(arrayList.get(roomIndex).getMurEst().getBitmap() != null) {
                        for (Button b: buttonPorteNord) {
                            b.setClickable(false);
                            b.setBackgroundColor(Color.TRANSPARENT);
                        }
                        setImageMurEst();
                        for (Button b: buttonPorteEst) {
                            b.setClickable(true);
                            b.setBackgroundColor(Color.YELLOW);
                        }
                    }
                    break;
                case "E":
                    if(arrayList.get(roomIndex).getMurSud().getBitmap() != null) {
                        for (Button b: buttonPorteEst) {
                            b.setClickable(false);
                            b.setBackgroundColor(Color.TRANSPARENT);
                        }
                        setImageMurSud();
                        for (Button b: buttonPorteSud) {
                            b.setClickable(true);
                            b.setBackgroundColor(Color.YELLOW);
                        }
                    }
                    break;
                case "S":
                    if(arrayList.get(roomIndex).getMurOuest().getBitmap() != null) {
                        for (Button b: buttonPorteSud) {
                            b.setClickable(false);
                            b.setBackgroundColor(Color.TRANSPARENT);
                        }
                        setImageMurOuest();
                        for (Button b: buttonPorteOuest) {
                            b.setClickable(true);
                            b.setBackgroundColor(Color.YELLOW);
                        }
                    }
                    break;
                case "O":
                    if(arrayList.get(roomIndex).getMurNord().getBitmap() != null) {
                        for (Button b: buttonPorteOuest) {
                            b.setClickable(false);
                            b.setBackgroundColor(Color.TRANSPARENT);
                        }
                        setImageMurNord();
                        for (Button b: buttonPorteNord) {
                            b.setClickable(true);
                            b.setBackgroundColor(Color.YELLOW);
                        }
                    }
                    break;
            }
            setupPortes();
        });

        setupPortes();

    }


    public void changeImage(String direction){

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


        switch (direction) {
            case "N":
                if(arrayList.get(roomIndex).getMurNord().getPortes().size() != 0 ) { // Si il existe des portes dans ce mur
                    //Crée un bouton avec la position du rectangle de la porte
                    int i=0;
                    for (DoorModel door : arrayList.get(roomIndex).getMurNord().getPortes()) {
                        if(!door.isAddedToView()) { // Si le bouton de la porte n'est pas encore ajouté
                            buttonPorteNord.add(new Button(this));
                            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.width = door.getPosition().width();
                            params.height = door.getPosition().height();
                            buttonPorteNord.get(i).setLayoutParams(params);
                            buttonPorteNord.get(i).setX(door.getPosition().left);
                            buttonPorteNord.get(i).setY(door.getPosition().top);
                            buttonPorteNord.get(i).setBackgroundColor(Color.YELLOW);
                            Log.i("TEST", "X=" + buttonPorteNord.get(i).getX() + "Y=" + buttonPorteNord.get(i).getY() + "W=" + buttonPorteNord.get(i).getWidth() + "H=" + buttonPorteNord.get(i).getHeight());
                            ViewRoomActivity.this.addContentView(buttonPorteNord.get(i), buttonPorteNord.get(i).getLayoutParams());
                            i++;
                            door.setAddedToView(true);
                        }
                    }
                }
                break;
            case "E":
                if(arrayList.get(roomIndex).getMurNord().getPortes().size() != 0) { // Si il existe des portes dans ce mur
                    //Crée un bouton avec la position du rectangle de la porte
                    int i=0;
                    for (DoorModel door : arrayList.get(roomIndex).getMurEst().getPortes()) {
                        if(!door.isAddedToView()) { // Si le bouton de la porte n'est pas encore ajouté
                            buttonPorteEst.add(new Button(this));
                            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.width = door.getPosition().width();
                            params.height = door.getPosition().height();
                            buttonPorteEst.get(i).setLayoutParams(params);
                            buttonPorteEst.get(i).setX(door.getPosition().left);
                            buttonPorteEst.get(i).setY(door.getPosition().top);
                            buttonPorteEst.get(i).setBackgroundColor(Color.YELLOW);
                            Log.i("TEST", "X=" + buttonPorteEst.get(i).getX() + "Y=" + buttonPorteEst.get(i).getY() + "W=" + buttonPorteEst.get(i).getWidth() + "H=" + buttonPorteEst.get(i).getHeight());
                            ViewRoomActivity.this.addContentView(buttonPorteEst.get(i), buttonPorteEst.get(i).getLayoutParams());
                            i++;
                            door.setAddedToView(true);
                        }
                    }
                }
                break;
            case "S":
                if(arrayList.get(roomIndex).getMurNord().getPortes().size() != 0) { // Si il existe des portes dans ce mur
                    //Crée un bouton avec la position du rectangle de la porte
                    int i = 0;
                    for (DoorModel door : arrayList.get(roomIndex).getMurSud().getPortes()) {
                        if(!door.isAddedToView()) { // Si le bouton de la porte n'est pas encore ajouté
                            buttonPorteSud.add(new Button(this));
                            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.width = door.getPosition().width();
                            params.height = door.getPosition().height();
                            buttonPorteSud.get(i).setLayoutParams(params);
                            buttonPorteSud.get(i).setX(door.getPosition().left);
                            buttonPorteSud.get(i).setY(door.getPosition().top);
                            buttonPorteSud.get(i).setBackgroundColor(Color.YELLOW);
                            Log.i("TEST", "X=" + buttonPorteSud.get(i).getX() + "Y=" + buttonPorteSud.get(i).getY() + "W=" + buttonPorteSud.get(i).getWidth() + "H=" + buttonPorteSud.get(i).getHeight());
                            ViewRoomActivity.this.addContentView(buttonPorteSud.get(i), buttonPorteSud.get(i).getLayoutParams());
                            i++;
                            door.setAddedToView(true);
                        }
                    }
                }
                break;
            case "O":
                if(arrayList.get(roomIndex).getMurNord().getPortes().size() != 0) { // Si il existe des portes dans ce mur
                    //Crée un bouton avec la position du rectangle de la porte
                    int i = 0;
                    for (DoorModel door : arrayList.get(roomIndex).getMurOuest().getPortes()) {
                        if(!door.isAddedToView()) { // Si le bouton de la porte n'est pas encore ajouté
                            buttonPorteOuest.add(new Button(this));
                            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.width = door.getPosition().width();
                            params.height = door.getPosition().height();
                            buttonPorteOuest.get(i).setLayoutParams(params);
                            buttonPorteOuest.get(i).setX(door.getPosition().left);
                            buttonPorteOuest.get(i).setY(door.getPosition().top);
                            buttonPorteOuest.get(i).setBackgroundColor(Color.YELLOW);
                            Log.i("TEST", "X=" + buttonPorteOuest.get(i).getX() + "Y=" + buttonPorteOuest.get(i).getY() + "W=" + buttonPorteOuest.get(i).getWidth() + "H=" + buttonPorteOuest.get(i).getHeight());
                            ViewRoomActivity.this.addContentView(buttonPorteOuest.get(i), buttonPorteOuest.get(i).getLayoutParams());
                            i++;
                            door.setAddedToView(true);
                        }
                    }
                }
                break;
        }

        ListenerPortes(buttonPorteNord);
        ListenerPortes(buttonPorteEst);
        ListenerPortes(buttonPorteSud);
        ListenerPortes(buttonPorteOuest);


    }

    private void ListenerPortes(ArrayList<Button> button) {
        for (Button b: button) {
            b.setOnClickListener((v)->{
                Toast.makeText(ViewRoomActivity.this, "Porte", Toast.LENGTH_SHORT).show();


            });
        }
    }

    @Override
    public void onBackPressed()
    {
        for (DoorModel d: arrayList.get(roomIndex).getMurNord().getPortes()) {
            d.setAddedToView(false);
        }
        for (DoorModel d: arrayList.get(roomIndex).getMurEst().getPortes()) {
            d.setAddedToView(false);
        }
        for (DoorModel d: arrayList.get(roomIndex).getMurSud().getPortes()) {
            d.setAddedToView(false);
        }
        for (DoorModel d: arrayList.get(roomIndex).getMurOuest().getPortes()) {
            d.setAddedToView(false);
        }
        this.finish();
    }


}