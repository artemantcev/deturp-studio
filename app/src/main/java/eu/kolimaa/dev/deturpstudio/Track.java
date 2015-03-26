package eu.kolimaa.dev.deturpstudio;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

/**
 * Describes the Track field entities which should appear inside the StartActivity's Track mPlaylistView
 */
public class Track {

    private String trackName;
    private Uri trackPath;
    private int trackLength;
    private boolean isMuted;

    public Track(String name, Uri path, Context context) {

        trackName = name;
        trackPath = path;

        try {
            MediaPlayer mp = MediaPlayer.create(context, trackPath);
            trackLength = mp.getDuration();
            mp.release();
        } catch (Exception e) {
            Log.d("Track.class", "error while trying to get" +
                    " the audio duration (file doesn't exist?)");
            AppHelper.killApplication();
        }

    }

    public void setMute(boolean isMuted) {
        this.isMuted = isMuted;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public int getTrackLengthInMsec() {
        return trackLength;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;

    }

    public void setTrackPath(Uri trackPath) {
        this.trackPath = trackPath;

    }

    public String getTrackName() {
        return trackName;
    }

    public Uri getTrackPath() {
        return trackPath;
    }


}
