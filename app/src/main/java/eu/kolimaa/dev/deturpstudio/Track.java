package eu.kolimaa.dev.deturpstudio;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

/**
 * Describes the Track field entities which should appear inside the StartActivity's Track mPlaylistView
 */
public class Track {

    private String trackName;
    private Uri trackPath;
    private MediaPlayer mp;

    public Track(String name, Uri path, Context context) {

        this.trackName = name;
        this.trackPath = path;

        try {

            mp = MediaPlayer.create(context, trackPath);

        } catch(Exception e) { Log.d("Track", "couldn't initialize MediaPlayer"); }

    }

    public void play() { //should be rewritten later
        mp.start();
    }

    public int getTrackLengthInMsec() {
        return mp.getDuration();
    }

    public Track setTrackName(String trackName) {
        this.trackName = trackName;

        return this;
    }

    public Track setTrackPath(Uri trackPath) {
        this.trackPath = trackPath;

        return this;
    }


    public String getTrackName() {
        return trackName;
    }

    public Uri getTrackPath() {
        return trackPath;
    }


}
