package com.nahl.berita.Intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nahl.berita.Main.MainActivity;
import com.nahl.berita.R;

public class SplashActivity extends AppCompatActivity implements IntroContract.ViewSplash {
    SharedPreferences sharedPreferences;
    private int waktu_loading = 4500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                checkIsFirstTimeOpen();

            }
        },waktu_loading);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                checkIsFirstTimeOpen();

            }
        },waktu_loading);



    }

    @Override
    public void checkIsFirstTimeOpen() {
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("userMasuk", false) != false){
            goToMainActivity();
        }else {
            goToIntroActivity();
        }

    }

    @Override
    public void goToMainActivity() {
        Intent goToMain = new Intent(SplashActivity.this, MainActivity.class);
//        goToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        goToMain.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(goToMain);
        finish();

    }

    @Override
    public void goToIntroActivity() {
        Intent goToIntro = new Intent(SplashActivity.this, IntroActivity.class);
//        goToIntro.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        goToIntro.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(goToIntro);
        finish();

    }
}
