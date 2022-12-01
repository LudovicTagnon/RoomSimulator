package com.example.roomsimulator;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<RoomModel> arrayList;

    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        //Récupère les rooms
        arrayList = RoomManager.getInstance().getArrayListRooms();
        recyclerView = findViewById(R.id.recycler_view_rooms);
        getRoomList();


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(NavigationActivity.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent ic = new Intent(NavigationActivity.this, ViewRoomActivity.class);
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
}