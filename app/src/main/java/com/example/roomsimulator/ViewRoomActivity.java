package com.example.roomsimulator;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONException;

import java.util.ArrayList;

public class ViewRoomActivity extends AppCompatActivity {

    private ArrayList<RoomModel> arrayList;
    private int roomIndex;
    private String direction;

    private ImageView imageAffichee;

    private Button buttonGauche;
    private Button buttonDroite;

    ArrayList<Button> buttonPorteNord = new ArrayList<>();
    ArrayList<Button> buttonPorteEst = new ArrayList<>();
    ArrayList<Button> buttonPorteSud = new ArrayList<>();
    ArrayList<Button> buttonPorteOuest = new ArrayList<>();

    WallModel MurNord;
    WallModel MurEst;
    WallModel MurSud;
    WallModel MurOuest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_room);

        //Lie les objets xml aux variables de l'activité
        imageAffichee = findViewById(R.id.imageViewCourante);
        buttonGauche = findViewById(R.id.buttonLeft);
        buttonDroite = findViewById(R.id.buttonRight);
        buttonGauche.setBackgroundColor(Color.CYAN);
        buttonDroite.setBackgroundColor(Color.CYAN);



        //Récupère l'index de la pièce sélectionnée dans la recyclerview
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("roomSelected");
            roomIndex = Integer.parseInt(value);
        }

        arrayList = RoomManager.getInstance().getArrayListRooms();        //Récupère les rooms

        setup_Murs_Portes();        //Affiche le mur et les portes

        appelBoutonGauche();        //Listener Bouton Gauche

        appelBoutonDroit();        //Listener Bouton Droit

        try {
            RoomManager.getInstance().printRooms(ViewRoomActivity.this);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void setup_Murs_Portes(){
        this.direction = "N";        //Direction Initiale

        //Récupère les murs de la pièce actuelle
        MurNord = arrayList.get(roomIndex).getMurNord();
        MurEst = arrayList.get(roomIndex).getMurEst();
        MurSud = arrayList.get(roomIndex).getMurSud();
        MurOuest = arrayList.get(roomIndex).getMurOuest();

        this.setImageMurChoisi(this.direction);        //Affiche le mur par défaut et Enregistre la nouvelle direction

        setupPortes();        //Met en place les portes

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

            for (Button b : buttonsApres) { // Désactive les portes de l'ancien mur
                b.setClickable(true);
                b.setBackgroundColor(Color.YELLOW);
            }
        }
    }


    public void setImageMurChoisi(String direction){
        if(direction.equals("N")){
            imageAffichee.setImageBitmap(arrayList.get(roomIndex).getMurNord().getBitmap());
        } else if(direction.equals("E")){
            imageAffichee.setImageBitmap(arrayList.get(roomIndex).getMurEst().getBitmap());
        }else if(direction.equals("S")){
            imageAffichee.setImageBitmap(arrayList.get(roomIndex).getMurSud().getBitmap());
        }else if(direction.equals("O")){
            imageAffichee.setImageBitmap(arrayList.get(roomIndex).getMurOuest().getBitmap());
        }
        this.direction = direction;
    }


    public void setupPortes(){

        ArrayList<Button> BouttonsDuMur = null;     // ArrayList temporaire pour afficher les bouttons
        WallModel MurActuel = null;

        //Enregistre le boutton actuel et le mur actuel
        switch (this.direction) {
            case "N":
                BouttonsDuMur = buttonPorteNord;
                MurActuel = MurNord;
                break;
            case "E":
                BouttonsDuMur = buttonPorteEst;
                MurActuel = MurEst;

                break;
            case "S":
                BouttonsDuMur = buttonPorteSud;
                MurActuel = MurSud;

                break;
            case "O":
                BouttonsDuMur = buttonPorteOuest;
                MurActuel = MurOuest;

                break;
        }

        assert MurActuel != null;
        if(MurActuel.getPortes().size() != 0 ) { // Si il existe des portes dans ce mur
            //Crée les boutons à partir de la position du rectangles de chaque porte
            int i=0;
            for (DoorModel door : MurActuel.getPortes()) { // Pour chaque porte
                if(!door.isAddedToView()) { // Si le bouton de la porte n'est pas encore ajouté
                    assert BouttonsDuMur != null;
                    BouttonsDuMur.add(new Button(this));
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.width = door.getPosition().width();
                    params.height = door.getPosition().height();
                    BouttonsDuMur.get(i).setLayoutParams(params);
                    BouttonsDuMur.get(i).setX(door.getPosition().left);
                    BouttonsDuMur.get(i).setY(door.getPosition().top);
                    //BouttonsDuMur.get(i).setBackgroundColor(Color.YELLOW);
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setStroke(2, Color.YELLOW);
                    BouttonsDuMur.get(i).setBackground(gradientDrawable);
                    BouttonsDuMur.get(i).setClickable(true);
                    //Toast.makeText(ViewRoomActivity.this, "Portes affichées", Toast.LENGTH_SHORT).show();

                    Log.i("TEST", "X=" + BouttonsDuMur.get(i).getX() + "Y=" + BouttonsDuMur.get(i).getY() + "W=" + BouttonsDuMur.get(i).getWidth() + "H=" + BouttonsDuMur.get(i).getHeight());
                    ViewRoomActivity.this.addContentView(BouttonsDuMur.get(i), BouttonsDuMur.get(i).getLayoutParams());
                    i++;
                    door.setAddedToView(true);
                }
            }
        }else{
            //Toast.makeText(ViewRoomActivity.this, "Pas de portes dans ce mur", Toast.LENGTH_SHORT).show();
        }


        ListenerPortes(buttonPorteNord, MurNord);
        ListenerPortes(buttonPorteEst, MurEst);
        ListenerPortes(buttonPorteSud, MurSud);
        ListenerPortes(buttonPorteOuest, MurOuest);

    }


    private void ListenerPortes(ArrayList<Button> buttons, WallModel Mur) {
        for (Button b: buttons) {
            b.setOnClickListener((v)->{ // la porte s'ouvre
                //Toast.makeText(ViewRoomActivity.this, "Porte", Toast.LENGTH_SHORT).show();

                int buttonIndex = buttons.indexOf(b);

                // Changement de pièce
                for (int i = 0; i < arrayList.size(); i++) {
                    if(arrayList.get(i).getName().equals(Mur.getPortes().get(buttonIndex).getNextRoom())){
                        this.roomIndex = i;
                    }
                }

                // Déclare que les portes ne sont plus dans la vue
                for (DoorModel d: Mur.getPortes()) {
                    d.setAddedToView(false);
                }
                DestroyAllButtons(); // Enlève les boutons de l'affichage et
                setup_Murs_Portes(); // on affiche la nouvelle image/portes

            });
        }
    }

    private void DestroyAllButtons(){
        for (Button b: buttonPorteNord) {
            b.setClickable(false);
            b.setBackgroundColor(Color.TRANSPARENT);
        }
        for (Button b: buttonPorteEst) {
            b.setClickable(false);
            b.setBackgroundColor(Color.TRANSPARENT);
        }
        for (Button b: buttonPorteSud) {
            b.setClickable(false);
            b.setBackgroundColor(Color.TRANSPARENT);
        }
        for (Button b: buttonPorteOuest) {
            b.setClickable(false);
            b.setBackgroundColor(Color.TRANSPARENT);
        }
        buttonPorteNord = new ArrayList<Button>();
        buttonPorteEst = new ArrayList<Button>();
        buttonPorteSud = new ArrayList<Button>();
        buttonPorteOuest = new ArrayList<Button>();
    }

}