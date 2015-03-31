package eu.kolimaa.dev.deturpstudio;

import java.util.ArrayList;

public interface IMusicServiceController {

    public void play(boolean switchTrack);

    public void stop();

    public void onRemove(int position);

    public void onAdd();

    public void setTracks(ArrayList<Track> tracks);

}
