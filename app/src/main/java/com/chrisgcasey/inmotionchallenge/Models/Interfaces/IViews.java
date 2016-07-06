package com.chrisgcasey.inmotionchallenge.Models.Interfaces;

import com.chrisgcasey.inmotionchallenge.Models.ImageItem;

import java.util.List;

/**
 * Created by Chris on 6/29/2016.
 */

/**************************************
 interface to communicate with views
 *************************************/
public interface IViews {

    void downloadComplete(List<ImageItem> urls);
    void downloadFailed();

}
