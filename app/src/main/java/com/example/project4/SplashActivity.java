package com.example.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private static final String KEY_FIRST_INSTALL = "KEY_FIRST_INSTALL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final OnBroardingSharePreferences onBroardingSharePreferences = new OnBroardingSharePreferences(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(onBroardingSharePreferences.getBooleanValue(KEY_FIRST_INSTALL)){
                    startActivity(MainActivity.class);

                }else {
                    startActivity(OnBoardingActivity.class);
                    onBroardingSharePreferences.putBooleanValue(KEY_FIRST_INSTALL,true);
                }
            }




        },2000);
    }
    private void startActivity(Class<?> clas) {
        Intent intent = new Intent(this,clas);
        startActivity(intent);
        finish();
    }
    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            // chua login
            Intent intent = new Intent(this,SignInActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}