package com.chrisgcasey.inmotionchallenge.Views;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.chrisgcasey.inmotionchallenge.R;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    ImageView imageView;
    Animation animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = (ImageView) findViewById(R.id.img_inMotion);
        beginAnimations();
        beginTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Splash", Toast.LENGTH_LONG).show();
        beginAnimations();
        beginTimer();
    }
    // three seconds after creation start GridActivity
    private void beginTimer() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                transferActivities();
            }
        }, 3000);
    }

    private void transferActivities() {
        Intent intent = new Intent(this, GridViewActivity.class);
        startActivity(intent);
        finish();

    }
    //logo animation
    private void beginAnimations() {
        animator = AnimationUtils.loadAnimation(this, R.anim.grow);
        imageView.startAnimation(animator);
    }

}
