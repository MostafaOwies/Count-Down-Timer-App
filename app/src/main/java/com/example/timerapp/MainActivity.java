package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {


    int minutes;
    int secs;
    SeekBar seekbar;
    TextView textView;
    String secsString;
    Button button;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    boolean counter = false;

    public void alarm(View view){



        if ( counter==false) {
            counter = true;
            seekbar.setEnabled(false);
             button = findViewById(R.id.goButton);
            button.setText("Stop !");
             countDownTimer=new CountDownTimer(seekbar.getProgress() * 1000+100, 1000) {
                public void onTick(long l) {
                    timerFunction((int) l / 1000);
                }

                public void onFinish() {
                    mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.awaken);
                    mediaPlayer.start();
                    Log.i(TAG, "onFinish: finshed");
                }
            }.start();

        }
        else if (counter==true){
            counter=false;
            seekbar.setEnabled(true);
            button.setText("GO !");
            mediaPlayer.stop();
            countDownTimer.cancel();
            seekbar.setProgress(30);
            textView.setText("0 : 30");

        //    mediaPlayer= MediaPlayer.create(this,R.raw.awaken);

        }



    }

      public void timerFunction (int secsLeft){
          minutes =secsLeft /60 ;
          secs = secsLeft-(minutes*60);
          secsString = Integer.toString(secs);

          if (secs<=9){
              secsString="0"+secsString;
          }

          textView.setText(Integer.toString(minutes)+" : " + secsString);
      }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  mediaPlayer= MediaPlayer.create(this,R.raw.awaken);

        int progress = 30;
         seekbar = findViewById(R.id.timerseekBar);
         textView = findViewById(R.id.timerCountDown);
        seekbar.setMax(600);
        seekbar.setProgress(progress);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                timerFunction(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });{

        }
    }
}