package eu.kolimaa.dev.deturpstudio;

import android.net.Uri;

public interface ITrackOperator {

    public void onCreateNewTrack(String name, Uri path);

    public void onEditExistingTrack(String newName, int position);

}
