package eu.kolimaa.dev.deturpstudio;

import java.util.ArrayList;

public interface IMusicServiceController {

    public void play();

    public void stop();

    public void remove(int position);

    public void setTracks(ArrayList<Track> tracks);

}
