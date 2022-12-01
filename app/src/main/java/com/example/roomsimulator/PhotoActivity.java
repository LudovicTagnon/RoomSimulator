package com.example.roomsimulator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoActivity extends AppCompatActivity {

    private int roomIndex;
    private String direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        //Récupère l'index de la pièce sélectionnée dans la recyclerview
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            roomIndex = Integer.parseInt(extras.getString("roomIndex"));
            direction = extras.getString("direction");
        }

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getPackageManager()) != null){
            startActivityForResult(i, 1);
        }else {
            Toast.makeText(PhotoActivity.this, "Erreur prise photo ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            FileOutputStream fos;
            try {
                fos = openFileOutput("image.data", MODE_APPEND);
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);


                if(direction.equals("N")){
                    RoomManager.getInstance().getArrayListRooms().get(roomIndex).getMurNord().getImage().setImageBitmap(imageBitmap);
                    RoomManager.getInstance().getArrayListRooms().get(roomIndex).getMurNord().setBitmap(imageBitmap);
                }else if(direction.equals("E")){
                    RoomManager.getInstance().getArrayListRooms().get(roomIndex).getMurEst().getImage().setImageBitmap(imageBitmap);
                    RoomManager.getInstance().getArrayListRooms().get(roomIndex).getMurEst().setBitmap(imageBitmap);
                }else if(direction.equals("S")){
                    RoomManager.getInstance().getArrayListRooms().get(roomIndex).getMurSud().getImage().setImageBitmap(imageBitmap);
                    RoomManager.getInstance().getArrayListRooms().get(roomIndex).getMurSud().setBitmap(imageBitmap);
                }else if(direction.equals("O")){
                    RoomManager.getInstance().getArrayListRooms().get(roomIndex).getMurOuest().getImage().setImageBitmap(imageBitmap);
                    RoomManager.getInstance().getArrayListRooms().get(roomIndex).getMurOuest().setBitmap(imageBitmap);
                }

                fos.flush();
                fos.close();
                this.finish();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}