package com.jackieloven001.swipetest.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    private Context m_context;
    RelativeLayout parentView;
    int windowwidth;
    int windowheight;
    int screenCenter;
    int x_cord, y_cord;
    int imageHeight;
    float y_cord_edited;

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //dunno what this does
        setContentView(R.layout.mainlayout);
        m_context = MainActivity.this;

        parentView = (RelativeLayout) findViewById(R.id.layoutview);
        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        windowheight = getWindowManager().getDefaultDisplay().getHeight();
        screenCenter = windowwidth / 2;
        int[] myImageList = new int[]{R.drawable.cats, R.drawable.baby1, R.drawable.sachin,
                R.drawable.cats, R.drawable.puppy};

        for (int i = 0; i < 5; i++) {
            LayoutInflater inflate = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View m_view = inflate.inflate(R.layout.custom_layout, null); // dunno what this does yet
            ImageView m_image = (ImageView) m_view.findViewById(R.id.sp_image); // oh I think you set parameters on this later
            LinearLayout m_topLayout = (LinearLayout) m_view.findViewById(R.id.sp_color);
            LinearLayout m_bottomLayout = (LinearLayout) m_view.findViewById(R.id.sp_linh);

            m_view.setLayoutParams(new LayoutParams((windowwidth - 80), 450));
            m_view.setX(40);
            m_view.setY(40);
            m_view.setTag(i);
            m_image.setBackgroundResource(myImageList[i]);

            imageHeight = m_view.getHeight();

            parentView.addView(m_view);

            /*if (i == 0) {
                m_view.setRotation(-1);
            } else if (i == 1) {
                m_view.setRotation(-5);

            } else if (i == 2) {
                m_view.setRotation(3);

            } else if (i == 3) {
                m_view.setRotation(7);

            } else if (i == 4) {
                m_view.setRotation(-2);

            } else if (i == 5) {
                m_view.setRotation(5);

            }*/

            m_topLayout.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    x_cord = (int) event.getRawX();
                    y_cord = (int) event.getRawY();

                    // change in x at the beginning is original (x coordinate - screencenter)

                    //y_cord_edited = (float) y_cord * 1 / 2;
                    m_view.setX(1/2 * screenCenter + (x_cord - screenCenter));
                    m_view.setY(m_view.getX());

                    //m_view.setY(y_cord - 275);
                    //if y cord is less than half of windowheight
                    /*if (y_cord < windowheight) {
                        m_view.setY(y_cord - 275);
                    } else {
                        m_view.setY(y_cord - 500);
                    }*/


                    //- (imageHeight / 2)
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            if (x_cord >= screenCenter) {

                                m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                            } else {

                                m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));

                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            m_view.setRotation(0);
                            parentView.removeView(m_view);
                            break;
                    }
                    return true;
                }
            });
        }

    }
}