package com.example.roomsimulator;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class EditRoomActivity extends AppCompatActivity {

    private ArrayList<RoomModel> arrayList;

    private RoomModel currentRoom;
    private int roomIndex;

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

        arrayList.get(roomIndex).setImageNord(findViewById(R.id.imageViewNord));
        arrayList.get(roomIndex).setImageEst(findViewById(R.id.imageViewEst));
        arrayList.get(roomIndex).setImageSud(findViewById(R.id.imageViewSud));
        arrayList.get(roomIndex).setImageOuest(findViewById(R.id.imageViewOuest));

        Button buttonNord = findViewById(R.id.buttonAjouterPhotoN);
        Button buttonEst = findViewById(R.id.buttonAjouterPhotoE);
        Button buttonSud = findViewById(R.id.buttonAjouterPhotoS);
        Button buttonOuest = findViewById(R.id.buttonAjouterPhotoO);

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
            buttonNord.setTextColor(Color.TRANSPARENT);

        });
        buttonSud.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "S");
            startActivity(ic);
            buttonNord.setTextColor(Color.TRANSPARENT);

        });
        buttonOuest.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "O");
            startActivity(ic);
            buttonNord.setTextColor(Color.TRANSPARENT);
        });

    }
}