package com.example.roomsimulator;

import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class AddDoorActivity extends AppCompatActivity {

    private int roomIndex;
    private String direction;

    private ImageView imageView;

    private ArrayList<RoomModel> arrayList;

    private RoomModel currentRoom;

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