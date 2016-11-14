package com.vivhp.qlct.splash_screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.vivhp.qlct.MainActivity;
import com.vivhp.qlct.R;

public class SplashScreen extends Activity {

    private ProgressBar proBar;
    private int STATUS_INT = 0;
    private int SECOND_TIME_OUT_PROGRESS_BAR = 100;
    private int SECOND_TIME_OUT = 3000;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        proBar = (ProgressBar) findViewById(R.id.proBar);

        //Set min / max ProgressBar
        proBar.setProgress(0);
        proBar.setMax(100);


        handler = new Handler();
        TimeOut(SECOND_TIME_OUT);
        //Delay 0,1s run ProgressBar
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            updateProgressBar();
//            }
//        }, SECOND_TIME_OUT_PROGRESS_BAR);


    }

//    private void updateProgressBar(){
//        //Tăng trạng thái cho progressBar
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (STATUS_INT < 100){
//                    //Tiến hành tăng status value
//                    STATUS_INT = updateStatus();
//                    try {
//                        //TimeOut 0.2s before update ProgressBar Status
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Update ProgressBar Value
//                            proBar.setProgress(STATUS_INT);
//                        }
//                    });
//                }
//                if (STATUS_INT >= 100){
//                    TimeOut(SECOND_TIME_OUT);
//                    STATUS_INT = 0;
//                }
//            }
//        });
//    }

    private int TimeOut(int time){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            }
        }, time);
        return time;
    }

//    private int updateStatus(){
//        if (STATUS_INT < 100){
//            STATUS_INT ++;
//        }
//        return STATUS_INT;
//    }
}
