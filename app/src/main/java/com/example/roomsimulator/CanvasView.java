package com.example.roomsimulator;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceView;
import android.view.View;

public class CanvasView extends View {

    private Paint paint;
    private SurfaceView surfaceView;
    private Rect rect;

    public CanvasView(Context context, SurfaceView surfaceView, Rect rect) {
        super(context);
        this.surfaceView = surfaceView;
        this.rect = rect;

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas = surfaceView.getHolder().lockCanvas();
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawRect(rect, paint);
        surfaceView.getHolder().unlockCanvasAndPost(canvas);

    }


    public void reDraw() {
        this.invalidate();
    }

}
