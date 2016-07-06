package com.chrisgcasey.inmotionchallenge.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Chris on 7/1/2016.
 */
public class Save {
    private Context mContext;
    private String mNameOfFolder = "/InMotion_App";
    private String mNameOfFile   = "InMotion_Photos";

    public void SaveImage(Context context, Bitmap imageToSave){
        mContext = context;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath()+ mNameOfFolder;
        Log.e("path", file_path);
        String CurrentDateAndTime= getCurrentDateAndTime();
        File dir = new File(file_path);

        if(!dir.exists()){
            dir.mkdirs();
            Log.e("File", "Exists");
        }

        File file = new File(dir, mNameOfFile +CurrentDateAndTime+ ".jpg");

        //stream to file
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
            //file saved
            AbleToSave();

        }
        catch (FileNotFoundException e) {
            //error saving
            UnableToSave();
            Log.e("MESSAGE", e.getMessage());
        }
        catch (IOException e){UnableToSave();}
    }
    //date and time for file extension
    private String getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    // there was an error in saving the file. let the user know
    private void UnableToSave() {
        Toast.makeText(mContext, "Picture cannot be saved", Toast.LENGTH_SHORT).show();

    }
    // inform the user that the file was saved successfully
    private void AbleToSave() {
        Toast.makeText(mContext, "Picture saved", Toast.LENGTH_SHORT).show();

    }
}