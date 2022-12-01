package com.example.roomsimulator;

import android.graphics.Rect;

import java.util.ArrayList;

public class DoorModel {

    private Rect position;
    private String nextRoom;
    private boolean isAddedToView = false;


    public DoorModel(Rect position) {
        this.position = position;
        this.isAddedToView = false;
    }

    public DoorModel(Rect position, String nextRoom) {
        this.position = position;
        this.nextRoom = nextRoom;
        this.isAddedToView = false;
    }

    public DoorModel() {
    }

    public DoorModel(String nextRoom) {
        this.nextRoom = nextRoom;
    }


    public Rect getPosition() {
        return position;
    }

    public String getNextRoom() {
        return nextRoom;
    }

    public void setPosition(Rect position) {
        this.position = position;
    }

    public void setNextRoom(String nextRoom) {
        this.nextRoom = nextRoom;
    }

    public boolean isAddedToView() {
        return isAddedToView;
    }

    public void setAddedToView(boolean addedToView) {
        isAddedToView = addedToView;
    }
}
