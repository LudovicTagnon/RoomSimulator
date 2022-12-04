package com.example.roomsimulator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Insets;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import android.view.WindowInsets;
import android.view.WindowMetrics;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class EditRoomActivity extends AppCompatActivity implements SensorEventListener{

    private ArrayList<RoomModel> arrayList;

    private RoomModel currentRoom;
    private int roomIndex;

    private ImageView imageNord;
    private ImageView imageEst;
    private ImageView imageSud;
    private ImageView imageOuest;

    //==============================================================================================
    private ImageView imageViewAiguille;
    private Sensor sensorMagneticField;
    private SensorManager SensorManager;
    private float DegreeStart = 0f;

    private float[] floatMagnetic = new float[3];
    private float[]  floatGravity = new float[3];
    private float[] floatOrientation = new float[3];
    private float[] floatRotationMatrix = new float[9];





    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Récupère l'index de la pièce sélectionnée dans la recyclerview
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("roomSelected");
            roomIndex = Integer.parseInt(value);
        }

        //Récupère les rooms
        arrayList = RoomManager.getInstance().getArrayListRooms();

        //Lie l'imageview xml à la variable imageview du room manager
        if(arrayList.size() > roomIndex){
            arrayList.get(roomIndex).getMurNord().setImage(findViewById(R.id.imageViewNord));
            arrayList.get(roomIndex).getMurEst().setImage(findViewById(R.id.imageViewEst));
            arrayList.get(roomIndex).getMurSud().setImage(findViewById(R.id.imageViewSud));
            arrayList.get(roomIndex).getMurOuest().setImage(findViewById(R.id.imageViewOuest));
        }

        //Lie les boutons xml aux boutons du code
        Button buttonImageNord = findViewById(R.id.buttonAjouterPhotoN);
        Button buttonImageEst = findViewById(R.id.buttonAjouterPhotoE);
        Button buttonImageSud = findViewById(R.id.buttonAjouterPhotoS);
        Button buttonImageOuest = findViewById(R.id.buttonAjouterPhotoO);


        //Affiche les images de chaque mur dans leur cadre respectif si elles existent

        if(arrayList.size() != 0){
            if(arrayList.get(roomIndex).getMurNord().getBitmap() != null) {
                arrayList.get(roomIndex).getMurNord().getImage().setImageBitmap(arrayList.get(roomIndex).getMurNord().getBitmap());
                buttonImageNord.setTextColor(Color.TRANSPARENT);
            }
            if(arrayList.get(roomIndex).getMurEst().getBitmap() != null) {
                arrayList.get(roomIndex).getMurEst().getImage().setImageBitmap(arrayList.get(roomIndex).getMurEst().getBitmap());
                buttonImageEst.setTextColor(Color.TRANSPARENT);
            }
            if(arrayList.get(roomIndex).getMurSud().getBitmap() != null) {
                arrayList.get(roomIndex).getMurSud().getImage().setImageBitmap(arrayList.get(roomIndex).getMurSud().getBitmap());
                buttonImageSud.setTextColor(Color.TRANSPARENT);
            }
            if(arrayList.get(roomIndex).getMurOuest().getBitmap() != null) {
                arrayList.get(roomIndex).getMurOuest().getImage().setImageBitmap(arrayList.get(roomIndex).getMurOuest().getBitmap());
                buttonImageOuest.setTextColor(Color.TRANSPARENT);
            }
        }


        buttonImageNord.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "N");
            startActivity(ic);
            buttonImageNord.setTextColor(Color.TRANSPARENT);

        });
        buttonImageEst.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "E");
            startActivity(ic);
            buttonImageEst.setTextColor(Color.TRANSPARENT);
        });
        buttonImageSud.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "S");
            startActivity(ic);
            buttonImageSud.setTextColor(Color.TRANSPARENT);
        });
        buttonImageOuest.setOnClickListener((v)->{
            Intent ic = new Intent(EditRoomActivity.this, PhotoActivity.class);
            ic.putExtra("roomIndex", Integer.toString(roomIndex));
            ic.putExtra("direction", "O");
            startActivity(ic);
            buttonImageOuest.setTextColor(Color.TRANSPARENT);
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


        //===============================================================================================


        imageViewAiguille = findViewById(R.id.imageViewCompass);

        SensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        // to stop the listener and save battery
        SensorManager.unregisterListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // code for system's orientation sensor registered listeners
        SensorManager.registerListener(this, SensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // get angle around the z-axis rotated
        float degree = Math.round(event.values[0]);
        // rotation animation - reverse turn degree degrees
        RotateAnimation ra = new RotateAnimation(
                DegreeStart,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        // set the compass animation after the end of the reservation status
        ra.setFillAfter(true);
        // set how long the animation for the compass image will take place
        ra.setDuration(210);
        // Start animation of compass image
        imageViewAiguille.startAnimation(ra);
        DegreeStart = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}