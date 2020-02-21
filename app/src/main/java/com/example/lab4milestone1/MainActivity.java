package com.example.lab4milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    class ExampleRunnable implements Runnable {

        int seconds;

        ExampleRunnable(int s) {
            seconds = s;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {
                if (stopThread) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startButton.setText(getString(R.string.startButton_text));
                        }
                    });
                    return;
                }
                if (i == 5) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startButton.setText(getString(R.string.startButton_text50));
                        }
                    });
                }
                Log.d(TAG,"startThread: "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startButton.setText(getString(R.string.startButton_text));
                }
            });
        }
    }

    private static final String TAG = "MainActivity";

    private volatile boolean stopThread = false;

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
    }

    public void startThread(View view) {
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(10);
        new Thread(runnable).start();
    }

    public void stopThread(View view) {
        stopThread = true;
    }
}
