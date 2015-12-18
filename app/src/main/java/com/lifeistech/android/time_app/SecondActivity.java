package com.lifeistech.android.time_app;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity {
    private int minNumber;
    private  int secNumber;

    private Timer timer;

    private SoundPool soundPool;
    private  int soundID;

    private boolean isTimer=false;
    private Button stopButton;
    private boolean isSound=false;
    private TextView textView1;
    private TextView textView2;
    private  android.os.Handler handler=new  android.os.Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView1=(TextView)findViewById(R.id.min);
        textView2=(TextView)findViewById(R.id.sec);

        Intent intent=getIntent();
        minNumber=intent.getIntExtra("minute",0)*10+intent.getIntExtra("minute2",0);

        secNumber=intent.getIntExtra("second",0)*10+intent.getIntExtra("second2",0);

        textView1.setText(String.valueOf(minNumber));
        textView2.setText(String.valueOf(secNumber));


        timer=new Timer(true);
        stopButton=(Button)findViewById(R.id.button3);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Viewの処理

                        if (minNumber==0&&secNumber==0){
                            timer.cancel();
                            timerSound();

                        }
                        Log.d("TAG",String.valueOf(minNumber));
                        Log.d("TAG",String.valueOf(secNumber));

                        textView1.setText(String.format("%02d",minNumber));
                        textView2.setText(String.format("%02d",secNumber));
                    }
                });
                //ここで数字の処理


                if (secNumber==0){
                    secNumber=59;
                    minNumber--;


                }else {
                    secNumber--;
                }

            }
        },1000,1000);//1000秒まってから１０００秒ミリごとに実行
        isTimer=true;

    }
    public void stop(View v){
        if (isTimer){
            timer.cancel();
            stopButton.setText("スタート");
            isTimer=false;
        }else {

            timer=new Timer(true);
            stopButton=(Button)findViewById(R.id.button3);


            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Viewの処理

                            if (minNumber == 0 && secNumber == 0) {
                                timer.cancel();
                                timerSound();

                            }
                            Log.d("TAG", String.valueOf(minNumber));
                            Log.d("TAG", String.valueOf(secNumber));

                            textView1.setText(String.format("%02d", minNumber));
                            textView2.setText(String.format("%02d", secNumber));
                        }
                    });
                    //ここで数字の処理


                    if (secNumber == 0) {
                        secNumber = 59;
                        minNumber--;


                    } else {
                        secNumber--;
                    }

                }
            }, 1000, 1000);
            stopButton.setText("ストップ");
            isTimer=true;






        }

        if (isSound){
            soundPool.pause(soundID);
            soundPool.release();
            isSound=false;
            finish();
        }
    }
    public void timerSound(){
        soundPool=new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = soundPool.load(getApplicationContext(), R.raw.sound, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            //読み込みが終わったら再生開始
//            読み込みが終わるというイベントを検知
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (0 == status) {
                    soundPool.play(soundID, 1.0F, 1.0F, 0, 0, 1.0F);
                }
            }
        });

        stopButton.setText("停止");
        isSound=true;





    }
}
