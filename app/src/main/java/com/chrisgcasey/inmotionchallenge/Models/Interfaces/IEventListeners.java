package com.chrisgcasey.inmotionchallenge.Models.Interfaces;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.chrisgcasey.inmotionchallenge.Models.ImageItem;

import java.util.List;

/**
 * Created by Chris on 6/28/2016.
 */

/*****************************
    interface to handle events
 ****************************/

public interface IEventListeners {

    void onButtonClicked(Activity activity, View view, String url);
    void onGridItemClicked(Context context, String url);
    void onDownloadSuccess(List<ImageItem> urls);
    void onDownloadFailed();
}
