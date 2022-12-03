package com.example.roomsimulator;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONException;

import java.util.ArrayList;

public class CreationActivity extends AppCompatActivity {

    private EditText inputText;
    private RecyclerView recyclerView;

    private ArrayList<RoomModel> arrayList;

    private MainAdapter adapter;

    private Button buttonNouvellePiece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        //Récupère les rooms
        arrayList = RoomManager.getInstance().getArrayListRooms();

        inputText = findViewById(R.id.textNouvellePiece);
        recyclerView = findViewById(R.id.recycler_view_rooms);
        getRoomList();
        
        buttonNouvellePiece = findViewById(R.id.buttonNouvellePiece);

        buttonNouvellePiece.setOnClickListener((v)->{
            RoomModel model = new RoomModel(String.valueOf(inputText.getText()));
            arrayList.add(model);
            getRoomList();

        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(CreationActivity.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent ic = new Intent(CreationActivity.this, EditRoomActivity.class);
                ic.putExtra("roomSelected", String.valueOf(position));
                startActivity(ic);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));



    }

    private void getRoomList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed()
    {
        Enregistrement enregistrement = new Enregistrement();
        try {
            enregistrement.save(CreationActivity.this, "storage.json");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        this.finish();
    }

}