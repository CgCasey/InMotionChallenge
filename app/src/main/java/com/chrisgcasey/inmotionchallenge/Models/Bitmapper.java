package com.chrisgcasey.inmotionchallenge.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Chris on 7/1/2016.
 */
public class Bitmapper {

    //convert into a bitmap to store on local disk
    public static Bitmap getBitmap(String urlString){
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        catch (IOException e){
            Log.e("IO", e.getMessage());
            return null;
        }
    }
}
