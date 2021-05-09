package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {


    protected void onCreate(Bundle savedInstanceStare) {
        super.onCreate(savedInstanceStare);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        startActivity(intent);

        finish();

    }



}