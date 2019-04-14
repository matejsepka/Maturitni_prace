package com.matej.sepka.appPackage;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.matej.sepka.appPackage.activity.MainActivity;

public class Splash extends AppCompatActivity {

    //on Create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout
        setContentView(R.layout.activity_splash);

        //handler spustí stránku jenom na urcitou dobu
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        },1000);
    }
}
