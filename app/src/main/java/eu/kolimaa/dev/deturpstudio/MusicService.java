package eu.kolimaa.dev.deturpstudio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.squareup.otto.Bus;

import java.util.ArrayList;

public class MusicService extends Service implements IMusicServiceController {

    private static MusicService msrv;
    private static Bus bus;

    private ArrayList<Track> tracks;
    private Track currentPlayingTrack;

    private MediaPlayer mp;

    public static Bus getServiceBus() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        msrv = this;
        getServiceBus().post(new ServiceEvent());


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static IMusicServiceController getControllerInstance() {
        return msrv;
    }

    @Override
    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public void play() {

        if (!tracks.isEmpty()) {

            if (mp == null) {
                currentPlayingTrack = tracks.listIterator().next();
                mp = MediaPlayer.create(getApplicationContext(),
                        currentPlayingTrack.getTrackPath());

            }

            if (mp.isPlaying()) {
                mp.pause();

            } else {
                mp.start();
            }

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
//                    mp.release();
                    mp.reset();
                    play();
                }
            });

        }
    }

    @Override
    public void stop() {
        if (!tracks.isEmpty()) {
            mp.seekTo(0);
        }
    }

    @Override
    public void remove(int position) {

        getServiceBus().post(new TrackRemoveEvent());

        if (tracks.get(position) == currentPlayingTrack) {
            mp.reset();
            mp.release();
            mp = null;
        }

    }

    @Override
    public void onDestroy() {
        msrv = null;
        super.onDestroy();
    }

}
