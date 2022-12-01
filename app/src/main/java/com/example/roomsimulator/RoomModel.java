package com.example.roomsimulator;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.ArrayList;

public class RoomModel {
    private String name;

    private ImageView imageNord = null;
    private ImageView imageEst = null;
    private ImageView imageSud = null;
    private ImageView imageOuest = null;

    private Bitmap imageNordBitmap;
    private Bitmap imageEstBitmap;
    private Bitmap imageSudBitmap;
    private Bitmap imageOuestBitmap;

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

    public Bitmap getImageNordBitmap() {
        return imageNordBitmap;
    }

    public Bitmap getImageEstBitmap() {
        return imageEstBitmap;
    }

    public Bitmap getImageSudBitmap() {
        return imageSudBitmap;
    }

    public Bitmap getImageOuestBitmap() {
        return imageOuestBitmap;
    }

    public void setImageNordBitmap(Bitmap imageNordBitmap) {
        this.imageNordBitmap = imageNordBitmap;
    }

    public void setImageEstBitmap(Bitmap imageEstBitmap) {
        this.imageEstBitmap = imageEstBitmap;
    }

    public void setImageSudBitmap(Bitmap imageSudBitmap) {
        this.imageSudBitmap = imageSudBitmap;
    }

    public void setImageOuestBitmap(Bitmap imageOuestBitmap) {
        this.imageOuestBitmap = imageOuestBitmap;
    }
}
