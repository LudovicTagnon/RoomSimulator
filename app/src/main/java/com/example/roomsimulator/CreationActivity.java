package com.example.roomsimulator;

import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CreationActivity extends AppCompatActivity {

    private EditText inputText;
    RecyclerView recyclerView;

    ArrayList<RoomModel> arrayList = new ArrayList<>();
    MainAdapter adapter;

    Button buttonNouvellePiece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);



        inputText = findViewById(R.id.textNouvellePiece);
        recyclerView = findViewById(R.id.recycler_view_rooms);

        buttonNouvellePiece = findViewById(R.id.buttonNouvellePiece);

        buttonNouvellePiece.setOnClickListener((v)->{
            RoomModel model = new RoomModel();
            model.setName(String.valueOf(inputText.getText()));
            arrayList.add(model);
            getRoomList();

        });



    }

    private void getRoomList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
    }


}