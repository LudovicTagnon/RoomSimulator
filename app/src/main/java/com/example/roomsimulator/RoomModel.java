package com.example.roomsimulator;

import android.widget.ImageView;

import java.util.ArrayList;

public class RoomModel {
    private String name;

    private ImageView imageNord;
    private ImageView imageEst;
    private ImageView imageSud;
    private ImageView imageOuest;


    public RoomModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ImageView getImageNord() {
        return imageNord;
    }

    public ImageView getImageEst() {
        return imageEst;
    }

    public ImageView getImageSud() {
        return imageSud;
    }

    public ImageView getImageOuest() {
        return imageOuest;
    }

    public void setImageNord(ImageView imageNord) {
        this.imageNord = imageNord;
    }

    public void setImageEst(ImageView imageEst) {
        this.imageEst = imageEst;
    }

    public void setImageSud(ImageView imageSud) {
        this.imageSud = imageSud;
    }

    public void setImageOuest(ImageView imageOuest) {
        this.imageOuest = imageOuest;
    }
}
