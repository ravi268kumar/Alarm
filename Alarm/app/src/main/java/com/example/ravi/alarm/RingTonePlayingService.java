package com.example.ravi.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ravi on 8/1/17.
 */

public class RingTonePlayingService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent , int flags,int startId){

        Log.e("Localservice" , "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy(){

        Toast.makeText(this, "on Destroy Cancell", Toast.LENGTH_SHORT).show();
    }
}
