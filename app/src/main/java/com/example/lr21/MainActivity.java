package com.example.lr21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;  //В переменных seconds и running хранится количество прошедших секунд и флаг работы секундомера
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }




    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }



    public void onClickStart(View view) {//Вызывается при щелчке на кнопке Start
        running = true;  //Запустить секундомер
    }

    public void onClickStop(View view) {//Вызывается при щелчке на кнопке Stop
        running = false; //Остановить секундомер
    }

    public void onClickReset(View view) {//Вызывается при щелчке на кнопке Reset
        running = false;
        seconds = 0;//Остановить секундомер и присвоить счетчику секунд 0
    }
    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.textView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 360000;
                int minutes = (seconds % 360000) / 6000;
                int secs = (seconds % 6000) / 100;
                int milisec = seconds % 100;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d:%02d", hours, minutes, secs, milisec);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1);
            }
        });
    }

}