package com.example.roomsimulator;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class AddDoorActivity extends AppCompatActivity {

    private int roomIndex;
    private String direction;

    private ImageView imageView;

    private ArrayList<RoomModel> arrayList;

    protected Rect rect;

    protected CanvasView canvasView;

    protected SurfaceView surfaceView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_door);


        //Récupère l'index de la pièce sélectionnée dans la recyclerview
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            roomIndex = Integer.parseInt(extras.getString("roomIndex"));
            direction = extras.getString("direction");
        }

        imageView = findViewById(R.id.imageViewAddDoor);

        //Récupère les rooms
        arrayList = RoomManager.getInstance().getArrayListRooms();

        if(direction.equals("N")){
            setNord();
        }else if(direction.equals("E")){
            setEst();
        }else if(direction.equals("S")){
            setSud();
        }else if(direction.equals("O")){
            setOuest();
        }


        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.setZOrderOnTop(true);
        surfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);


        imageView.setOnTouchListener(
                new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getPointerCount() == 2) {

                            rect = new Rect((int) event.getX(0), (int) event.getY(0)-100, (int) event.getX(1), (int) event.getY(1)-100);
                            rect.sort();

                            canvasView = new CanvasView(AddDoorActivity.this, surfaceView, rect);
                            AddDoorActivity.this.addContentView(canvasView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                            canvasView.reDraw();


                        }

                        if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                            Log.d("TouchTest", "Touch up");

                            View z = imageView.getRootView();
                            z.setDrawingCacheEnabled(true);
                            z.buildDrawingCache(true);

                            Bitmap bm0 = Bitmap.createBitmap(z.getDrawingCache());
                            Bitmap bm = Bitmap.createBitmap(bm0, (int) rect.left+50, (int) imageView.getY()+ 350 +rect.top, rect.width(), rect.height());


                            ImageView imageView2 = new ImageView(AddDoorActivity.this);
                            imageView2.setImageBitmap(bm);

                            Dialog dialog = new Dialog(AddDoorActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {

                                }
                            });

                            dialog.addContentView(imageView2, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            dialog.show();

                        }

                        return true;
                    }
                }
        );
    }

    public void setNord(){
        if(arrayList.get(roomIndex).getImageNordBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getImageNordBitmap());
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 1000;
            this.direction = "N";
        }
    }

    public void setEst(){
        if(arrayList.get(roomIndex).getImageEstBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getImageEstBitmap());
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 1000;
            this.direction = "E";
        }
    }

    public void setSud(){
        if(arrayList.get(roomIndex).getImageSudBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getImageSudBitmap());
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 1000;
            this.direction = "S";
        }
    }

    public void setOuest(){
        if(arrayList.get(roomIndex).getImageOuestBitmap() != null){
            imageView.setImageBitmap(arrayList.get(roomIndex).getImageOuestBitmap());
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 1000;
            this.direction = "O";
        }
    }



}