package com.glandroid.girl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView iv;
    private Bitmap newBitmap;
    private int startX;
    private int startY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        iv.setOnTouchListener(this);
    }

    private void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pre19);

        // 得到原图的拷贝
        newBitmap = bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap, new Matrix(), paint);
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();
                startY = (int) event.getY();
                System.out.println("按下：" + startX + "," + startY);
                newBitmap.setPixel(startX, startY, Color.TRANSPARENT);
                iv.setImageBitmap(newBitmap);
                break;
            case MotionEvent.ACTION_MOVE:
                int newX = (int) event.getX();
                int newY = (int) event.getY();
                System.out.println("移动：" + newX + "," + newY);
                for (int i = -3; i < 4; i++) {
                    for (int j = -3; j < 4; j++) {
                        newBitmap.setPixel(newX + i, newY + j, Color.TRANSPARENT);
                    }
                }
                iv.setImageBitmap(newBitmap);
                break;
            case MotionEvent.ACTION_UP:

                break;
        
            default:
                break;
        }
        return true;
    }
}
