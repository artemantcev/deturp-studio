package eu.kolimaa.dev.deturpstudio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class MusicService extends Service implements StartActivity.MusicPlayerController {

    private static MusicService msrv;
    private ArrayList<Track> tracks;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        msrv = this;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void play() {
        Log.d("MusicService", "play() method has been called");
    }

    @Override
    public void stop() {
        Log.d("MusicService", "stop() method has been called");
        Log.d("MusicServiceTest", tracks.get(0).getTrackName());
    }

    @Override
    public void pause() {
        Log.d("MusicService", "pause() method has been called");
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


}
