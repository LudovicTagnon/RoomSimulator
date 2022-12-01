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

    WallModel MurNord;
    WallModel MurEst;
    WallModel MurSud;
    WallModel MurOuest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_room);

        //Lie les objets xml aux variables de l'activité
        imageView = findViewById(R.id.imageViewCourante);
        buttonGauche = findViewById(R.id.buttonLeft);
        buttonDroite = findViewById(R.id.buttonRight);

        //Récupère l'index de la pièce sélectionnée dans la recyclerview
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("roomSelected");
            roomIndex = Integer.parseInt(value);
        }

        //Récupère les rooms
        arrayList = RoomManager.getInstance().getArrayListRooms();

        setup();

        //Listener Bouton Gauche
        appelBoutonGauche();

        //Listener Bouton Droit
        appelBoutonDroit();

    }

    private void setup(){
        //Direction Initiale
        this.direction = "N";

        //Récupère les murs
        MurNord = arrayList.get(roomIndex).getMurNord();
        MurEst = arrayList.get(roomIndex).getMurEst();
        MurSud = arrayList.get(roomIndex).getMurSud();
        MurOuest = arrayList.get(roomIndex).getMurOuest();

        //Image par défaut
        this.setImageMurChoisi("N");

        //Met en place les portes
        setupPortes();
    }

    private void ListenerPortes(ArrayList<Button> buttons, WallModel Mur) {
        for (Button b: buttons) {
            b.setOnClickListener((v)->{
                Toast.makeText(ViewRoomActivity.this, "Porte", Toast.LENGTH_SHORT).show();

                int buttonIndex = buttons.indexOf(b);

                for (int i = 0; i < arrayList.size(); i++) {
                    if(arrayList.get(i).getName().equals(Mur.getPortes().get(buttonIndex).getNextRoom())){
                        this.roomIndex = i;
                    }
                }
                setup();

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



    public void appelBoutonGauche(){
        buttonGauche.setOnClickListener((v)->{
            switch (direction) {
                case "N":
                    if(MurOuest.getBitmap()!=null) {
                        changeImage("N", "O");
                    }
                    break;
                case "E":
                    if(MurNord.getBitmap()!=null) {
                        changeImage("E", "N");
                    }
                    break;
                case "S":
                    if(MurEst.getBitmap()!=null) {
                        changeImage("S", "E");
                    }
                    break;
                case "O":
                    if(MurSud.getBitmap()!=null) {
                        changeImage("O", "S");
                    }
                    break;
            }
            setupPortes();
        });
    }

    public void appelBoutonDroit(){
        buttonDroite.setOnClickListener((v)->{
            switch (direction) {
                case "N":
                    if(MurEst.getBitmap()!=null) {
                        changeImage("N", "E");
                    }
                    break;
                case "E":
                    if(MurSud.getBitmap()!=null) {
                        changeImage("E", "S");
                    }
                    break;
                case "S":
                    if(MurOuest.getBitmap()!=null) {
                        changeImage("S", "O");
                    }
                    break;
                case "O":
                    if(MurNord.getBitmap()!=null) {
                        changeImage("O", "N");
                    }
                    break;
            }
            setupPortes();
        });
    }


    public void changeImage(String directionAvant, String directionApres){
        if ((directionAvant.equals("N") || directionAvant.equals("E") || directionAvant.equals("S") || directionAvant.equals("O")) && (directionApres.equals("N") || directionApres.equals("E") || directionApres.equals("S") || directionApres.equals("O")) ) {
            WallModel wallAvant = null;
            WallModel wallApres = null;
            ArrayList<Button> buttonsAvant = null;
            ArrayList<Button> buttonsApres = null;

            switch (directionAvant) {
                case "N":
                    wallAvant = this.MurNord;
                    buttonsAvant = buttonPorteNord;
                    break;
                case "E":
                    wallAvant = this.MurEst;
                    buttonsAvant = buttonPorteEst;
                    break;
                case "S":
                    wallAvant = this.MurSud;
                    buttonsAvant = buttonPorteSud;
                    break;
                case "O":
                    wallAvant = this.MurOuest;
                    buttonsAvant = buttonPorteOuest;
                    break;
            }
            for (Button b : buttonsAvant) {
                b.setClickable(false);
                b.setBackgroundColor(Color.TRANSPARENT);
            }
            switch (directionApres) {
                case "N":
                    wallApres = this.MurNord;
                    buttonsApres = buttonPorteNord;
                    setImageMurChoisi("N");
                    break;
                case "E":
                    wallApres = this.MurEst;
                    buttonsApres = buttonPorteEst;
                    setImageMurChoisi("E");
                    break;
                case "S":
                    wallApres = this.MurSud;
                    buttonsApres = buttonPorteSud;
                    setImageMurChoisi("S");
                    break;
                case "O":
                    wallApres = this.MurOuest;
                    buttonsApres = buttonPorteOuest;
                    setImageMurChoisi("O");
                    break;
            }

            for (Button b : buttonsApres) {
                b.setClickable(true);
                b.setBackgroundColor(Color.YELLOW);
            }
        }
    }


    public void setImageMurChoisi(String direction){
        if(direction.equals("N")){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurNord().getBitmap());
        } else if(direction.equals("E")){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurEst().getBitmap());
        }else if(direction.equals("S")){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurSud().getBitmap());
        }else if(direction.equals("O")){
            imageView.setImageBitmap(arrayList.get(roomIndex).getMurOuest().getBitmap());
        }
        this.direction = direction;
    }


    public void setupPortes(){

        ArrayList<Button> buttonActuel = null;
        WallModel MurActuel = null;

        //Selon la direction actuelle
        switch (this.direction) {
            case "N":
                buttonActuel = buttonPorteNord;
                MurActuel = MurNord;
                break;
            case "E":
                buttonActuel = buttonPorteEst;
                MurActuel = MurEst;

                break;
            case "S":
                buttonActuel = buttonPorteSud;
                MurActuel = MurSud;

                break;
            case "O":
                buttonActuel = buttonPorteOuest;
                MurActuel = MurOuest;

                break;
        }

        assert MurActuel != null;
        if(MurActuel.getPortes().size() != 0 ) { // Si il existe des portes dans ce mur
            //Crée un bouton avec la position du rectangle de la porte
            int i=0;
            for (DoorModel door : MurActuel.getPortes()) {
                if(!door.isAddedToView()) { // Si le bouton de la porte n'est pas encore ajouté
                    assert buttonActuel != null;
                    buttonActuel.add(new Button(this));
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.width = door.getPosition().width();
                    params.height = door.getPosition().height();
                    buttonActuel.get(i).setLayoutParams(params);
                    buttonActuel.get(i).setX(door.getPosition().left);
                    buttonActuel.get(i).setY(door.getPosition().top);
                    buttonActuel.get(i).setBackgroundColor(Color.YELLOW);
                    Log.i("TEST", "X=" + buttonActuel.get(i).getX() + "Y=" + buttonActuel.get(i).getY() + "W=" + buttonActuel.get(i).getWidth() + "H=" + buttonActuel.get(i).getHeight());
                    ViewRoomActivity.this.addContentView(buttonActuel.get(i), buttonActuel.get(i).getLayoutParams());
                    i++;
                    door.setAddedToView(true);
                }
            }
        }


        ListenerPortes(buttonPorteNord, MurNord);
        ListenerPortes(buttonPorteEst, MurEst);
        ListenerPortes(buttonPorteSud, MurSud);
        ListenerPortes(buttonPorteOuest, MurOuest);


    }


}