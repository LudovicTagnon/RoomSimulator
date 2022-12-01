package com.example.roomsimulator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class EditRoomActivity extends AppCompatActivity {

    private ArrayList<RoomModel> arrayList;

    private RoomModel currentRoom;
    private int roomIndex;

    private ImageView imageNord;
    private ImageView imageEst;
    private ImageView imageSud;
    private ImageView imageOuest;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        //Récupère l'index de la pièce sélectionnée dans la recyclerview
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("roomSelected");
            roomIndex = Integer.parseInt(value);
        }

        //Récupère les rooms
        arrayList = RoomManager.getInstance().getArrayListRooms();

        //Lie l'imageview xml à la variable imageview du room manager
        arrayList.get(roomIndex).getMurNord().setImage(findViewById(R.id.imageViewNord));
        arrayList.get(roomIndex).getMurEst().setImage(findViewById(R.id.imageViewEst));
        arrayList.get(roomIndex).getMurSud().setImage(findViewById(R.id.imageViewSud));
        arrayList.get(roomIndex).getMurOuest().setImage(findViewById(R.id.imageViewOuest));

        //Lie les boutons xml aux boutons du code
        Button buttonNord = findViewById(R.id.buttonAjouterPhotoN);
        Button buttonEst = findViewById(R.id.buttonAjouterPhotoE);
        Button buttonSud = findViewById(R.id.buttonAjouterPhotoS);
        Button buttonOuest = findViewById(R.id.buttonAjouterPhotoO);


        //Affiche les images de chaque mur dans leur cadre respectif si elles existent

        if(arrayList.get(roomIndex).getMurNord().getBitmap() != null) {
            arrayList.get(roomIndex).getMurNord().getImage().setImageBitmap(arrayList.get(roomIndex).getMurNord().getBitmap());
            buttonNord.setTextColor(Color.TRANSPARENT);
        }
        if(arrayList.get(roomIndex).getMurEst().getBitmap() != null) {
            arrayList.get(roomIndex).getMurEst().getImage().setImageBitmap(arrayList.get(roomIndex).getMurEst().getBitmap());
            buttonEst.setTextColor(Color.TRANSPARENT);
        }
        if(arrayList.get(roomIndex).getMurSud().getBitmap() != null) {
            arrayList.get(roomIndex).getMurSud().getImage().setImageBitmap(arrayList.get(roomIndex).getMurSud().getBitmap());
            buttonSud.setTextColor(Color.TRANSPARENT);
        }
        if(arrayList.get(roomIndex).getMurOuest().getBitmap() != null) {
            arrayList.get(roomIndex).getMurOuest().getImage().setImageBitmap(arrayList.get(roomIndex).getMurOuest().getBitmap());
            buttonOuest.setTextColor(Color.TRANSPARENT);
        }


        buttonNord.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "N");
            startActivity(ic);
            buttonNord.setTextColor(Color.TRANSPARENT);
        });
        buttonEst.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "E");
            startActivity(ic);
            buttonEst.setTextColor(Color.TRANSPARENT);
        });
        buttonSud.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "S");
            startActivity(ic);
            buttonSud.setTextColor(Color.TRANSPARENT);
        });
        buttonOuest.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "O");
            startActivity(ic);
            buttonOuest.setTextColor(Color.TRANSPARENT);
        });

        ImageButton buttonPorteNord = findViewById(R.id.buttonPorteNord);
        ImageButton buttonPorteEst = findViewById(R.id.buttonPorteEst);
        ImageButton buttonPorteSud = findViewById(R.id.buttonPorteSud);
        ImageButton buttonPorteOuest = findViewById(R.id.buttonPorteOuest);

        buttonPorteNord.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, AddDoorActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "N");
            startActivity(ic);
        });
        buttonPorteEst.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, AddDoorActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "E");
            startActivity(ic);
        });
        buttonPorteSud.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, AddDoorActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "S");
            startActivity(ic);
        });
        buttonPorteOuest.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, AddDoorActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "O");
            startActivity(ic);
        });

    }
}