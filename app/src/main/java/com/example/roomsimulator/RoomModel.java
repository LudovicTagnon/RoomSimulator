package com.example.roomsimulator;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.ArrayList;

public class RoomModel {
    private String name;

    private WallModel murNord;
    private WallModel murEst;
    private WallModel murSud;
    private WallModel murOuest;


    public RoomModel(String name) {
        this.name = name;
        this.murNord = new WallModel("N");
        this.murEst = new WallModel("E");
        this.murSud = new WallModel("S");
        this.murOuest = new WallModel("O");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public WallModel getMurNord() {
        return murNord;
    }

    public WallModel getMurEst() {
        return murEst;
    }

    public WallModel getMurSud() {
        return murSud;
    }

    public WallModel getMurOuest() {
        return murOuest;
    }

    public void setMurNord(WallModel murNord) {
        this.murNord = murNord;
    }

    public void setMurEst(WallModel murEst) {
        this.murEst = murEst;
    }

    public void setMurSud(WallModel murSud) {
        this.murSud = murSud;
    }

    public void setMurOuest(WallModel murOuest) {
        this.murOuest = murOuest;
    }

    @Override
    public String toString() {
        return "RoomModel{" +
                "name='" + name + '\'' +
                ", murNord=" + murNord +
                ", murEst=" + murEst +
                ", murSud=" + murSud +
                ", murOuest=" + murOuest +
                '}';
    }
}
