package com.smart.joe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class DragActivity extends Activity implements View.OnTouchListener {
    private final static String TAG = DragActivity.class.getName();
    private static final int START_DRAGGING = 0;
    private static final int STOP_DRAGGING = 1;
    private int status;
    private ImageView image;
    private Button btn;
    private FrameLayout layout;
    private FrameLayout.LayoutParams params;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        layout = (FrameLayout) findViewById(R.id.frame_layout);
        btn = (Button) findViewById(R.id.button1);
        btn.setDrawingCacheEnabled(true);
        btn.setOnTouchListener(this);

        params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            status = START_DRAGGING;
            image = new ImageView(this);
            image.setImageBitmap(btn.getDrawingCache());
            layout.addView(image,params);
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            status = STOP_DRAGGING;
            Log.i(TAG, "stop drag");
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE){
           if(status == START_DRAGGING){
               image.setPadding((int)event.getRawX(),(int)event.getRawY(),0,0);
               image.invalidate();
           }
        }
        Log.i(TAG, "On touch");
        return false;
    }
}
