package com.chrisgcasey.inmotionchallenge.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chrisgcasey.inmotionchallenge.Presenter.MainPresenter;
import com.chrisgcasey.inmotionchallenge.R;
import com.squareup.picasso.Picasso;

public class ViewImageActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    Button saveButton;
    Button galleryButton;
    Animation animator;
    View.OnClickListener clickListener;
    MainPresenter presenter;
    Intent intent;
    String mUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        intent = getIntent();
        mUrl = intent.getStringExtra("Url");

        imageView = (ImageView) findViewById(R.id.imgview_low_res);
        saveButton = (Button) findViewById(R.id.btn_save);
        galleryButton = (Button) findViewById(R.id.btn_gallery);
        saveButton.setOnClickListener(this);
        galleryButton.setOnClickListener(this);

        presenter = MainPresenter.getInstance(null);

        beginAnimations();

        Picasso.with(this).load(mUrl).fit().placeholder(R.drawable.placeholder).into(imageView);
    }

    private void beginAnimations() {
        animator = AnimationUtils.loadAnimation(this, R.anim.grow);
        imageView.startAnimation(animator);
        saveButton.startAnimation(animator);
        galleryButton.startAnimation(animator);
    }
    //handle button clicks
    @Override
    public void onClick(View view) {
        presenter.onButtonClicked(this, view, mUrl);

    }
}
