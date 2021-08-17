package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    int minutes;
    int secs;
    SeekBar seekbar;
    TextView textView;
    String secsString;
    Button button;
    MediaPlayer mediaPlayer;
    int x = 1;

    public void alarm(View view){



        if ( x ==1) {
             button = findViewById(R.id.goButton);
            button.setText("Stop !");
            new CountDownTimer(seekbar.getProgress() * 1000, 1000) {
                public void onTick(long l) {
                    timerFunction((int) l / 1000);
                }

                public void onFinish() {
                    mediaPlayer.start();
                }
            }.start();
            x =2;
        }
        else if (x==2){
            x=1;
            button.setText("GO !");
            
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
        mediaPlayer= MediaPlayer.create(this,R.raw.awaken);

         seekbar = findViewById(R.id.timerseekBar);
         textView = findViewById(R.id.timerCountDown);
        seekbar.setMax(600);
        seekbar.setProgress(0);

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