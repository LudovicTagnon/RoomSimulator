package com.example.roomsimulator;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.widget.ImageView;

import java.util.ArrayList;

public class WallModel {
    private String direction;
    private ImageView image;

    private Bitmap bitmap;
    private ArrayList<Rect> portes = new ArrayList<>();

    public WallModel(String direction) {
        this.direction = direction;
    }


    public String getDirection() {
        return direction;
    }

    public ImageView getImage() {
        return image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public ArrayList<Rect> getPortes() {
        return portes;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setPortes(ArrayList<Rect> portes) {
        this.portes = portes;
    }
}