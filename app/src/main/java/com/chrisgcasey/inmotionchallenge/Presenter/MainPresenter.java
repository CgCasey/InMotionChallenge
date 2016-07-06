package com.chrisgcasey.inmotionchallenge.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;

import com.chrisgcasey.inmotionchallenge.Models.AsyncTasker;
import com.chrisgcasey.inmotionchallenge.Models.Bitmapper;
import com.chrisgcasey.inmotionchallenge.Models.Constants;
import com.chrisgcasey.inmotionchallenge.Models.ImageItem;
import com.chrisgcasey.inmotionchallenge.Models.Interfaces.IEventListeners;
import com.chrisgcasey.inmotionchallenge.Models.Interfaces.IViews;
import com.chrisgcasey.inmotionchallenge.Models.MyRecyclerAdapter;
import com.chrisgcasey.inmotionchallenge.Models.Save;
import com.chrisgcasey.inmotionchallenge.R;
import com.chrisgcasey.inmotionchallenge.Views.GridViewActivity;
import com.chrisgcasey.inmotionchallenge.Views.ViewImageActivity;

import java.util.List;

/**
 * Created by Chris on 6/28/2016.
 */

// Presenter class to act as presenter in MVP architecture
public class MainPresenter implements IEventListeners{
    public IViews mGridView;
    public static MainPresenter presenter;
    MyRecyclerAdapter mRecyclerAdapter;

    public MainPresenter(){}

    private MainPresenter(IViews gridView) {
        this.mGridView = gridView;
        mRecyclerAdapter = new MyRecyclerAdapter();
    }
    // create a singleton presenter object
    public static MainPresenter getInstance(IViews iView) {
        if (presenter == null){
            presenter = new MainPresenter(iView);
        }
        return presenter;
    }
    //when activity is created create asynchronous task and download photos
    public void downloadPhotos(){
        AsyncTasker asyncTasker = new AsyncTasker();
        asyncTasker.execute(Constants.IG_USER_MEDIA_URL);
    }
    //button clicked on photo detail activity. parse which button and handle event
    @Override
    public void onButtonClicked(Activity activity, View view, String url) {
        switch (view.getId()){
            case R.id.btn_save :
                Bitmap bitmap = Bitmapper.getBitmap(url);
                Save save = new Save();
                save.SaveImage(activity, bitmap);
            case R.id.btn_gallery :
                activity.finish();
                break;
        }
    }
    // handle photo being clicked and enlarge in new view
    @Override
    public void onGridItemClicked(Context context, String url) {
        Intent intent = new Intent(context, ViewImageActivity.class);
        intent.putExtra("Url", url);
        context.startActivity(intent);
    }
    // download was successfull pass back to view for rendering
    @Override
    public void onDownloadSuccess(List<ImageItem> urls) {
        mGridView.downloadComplete(urls);

    }
    @Override
    // download failed update view
    public void onDownloadFailed(){
        mGridView.downloadFailed();
    }



    public void onSave(){

    }
}
