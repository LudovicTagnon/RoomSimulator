package com.example.roomsimulator;

import android.graphics.Rect;

import java.util.ArrayList;

public class DoorModel {

    private Rect position;

    private RoomModel nextRoom;

    private boolean isAddedToView = false;

    public DoorModel(Rect position) {
        this.position = position;
    }


    public Rect getPosition() {
        return position;
    }

    public RoomModel getNextRoom() {
        return nextRoom;
    }

    public void setPosition(Rect position) {
        this.position = position;
    }

    public void setNextRoom(RoomModel nextRoom) {
        this.nextRoom = nextRoom;
    }

    public boolean isAddedToView() {
        return isAddedToView;
    }

    public void setAddedToView(boolean addedToView) {
        isAddedToView = addedToView;
    }
}
