package eu.kolimaa.dev.deturpstudio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MusicService extends Service implements StartActivity.MusicPlayerController {
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void play() {

    }

    public void stop() {

    }

    public void pause() {

    }

}
