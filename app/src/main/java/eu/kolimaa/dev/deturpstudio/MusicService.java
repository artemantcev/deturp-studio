package eu.kolimaa.dev.deturpstudio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import com.squareup.otto.Bus;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service implements StartActivity.MusicPlayerController {

    private static MusicService msrv;
    private static Bus bus;

    private ArrayList<Track> tracks;
    private MediaPlayer mp;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        msrv = this;
        getServiceBus().post(new ServiceEvent());

        Uri testUri = Uri.parse("file:///sdcard/Music/Apoptose - Asche.mp3");
        mp = MediaPlayer.create(getApplicationContext(), testUri);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void play() {
        Log.d("MusicService", "play() method has been called");
        if (mp.isPlaying()) {
            mp.pause();
        } else {
            mp.start();
        }
    }

    @Override
    public void stop() {
        Log.d("MusicService", "stop() method has been called");
        mp.release();

    }


    @Override
    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public static MusicService getInstance() {
        return msrv;
    }

    @Override
    public void onDestroy() {
        msrv = null;
        super.onDestroy();
    }

    public static Bus getServiceBus() {
        if (bus == null) {
            bus = new Bus();
        }

        return bus;
    }


}
