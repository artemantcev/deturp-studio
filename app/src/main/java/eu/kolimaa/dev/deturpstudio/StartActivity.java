package eu.kolimaa.dev.deturpstudio;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class StartActivity extends Activity implements EditDialogFragment.TrackOperator, AdapterView.OnItemLongClickListener {

    ArrayList<Track> playListTracks;

    TrackListAdapter trackListAdapter;

    EditDialogFragment newTrackDialogFragment, editTrackDialogFragment;

    FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        playListTracks = new ArrayList<>();
        trackListAdapter = new TrackListAdapter(getApplicationContext(), playListTracks);

        final ListView playlistView = (ListView) findViewById(R.id.playlist);
        playlistView.setAdapter(trackListAdapter); //setting the adapter for playlist view

        playlistView.setOnItemLongClickListener(this);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

        Track currentTrack = playListTracks.get(position);

        editTrackDialogFragment = EditDialogFragment.newInstance(false);

        editTrackDialogFragment.setTrackPosition(position)
                .setCurrentName(currentTrack.getTrackName())
                .setCurrentFilePath(currentTrack.getTrackPath());

        editTrackDialogFragment.show(fm, "editTrackDialog");
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_start, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_quitbutton:
                AppHelper.killApplication();
            case R.id.action_add:
                if (newTrackDialogFragment == null) {
                    newTrackDialogFragment = EditDialogFragment.newInstance(true);
                }
                newTrackDialogFragment.show(fm, "newTrackDialog");

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateNewTrack(String name, Uri path) {
        Log.d("onCreateNewTrack", "HI!");
        Track track = new Track(name, path, getApplicationContext());
        playListTracks.add(track);

        trackListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditExistingTrack(String newName, Uri newPath, int position) {
        Log.d("onEditExistingTrack", "HI!");
        playListTracks.get(position)
                .setTrackName(newName)
                .setTrackPath(newPath);

        trackListAdapter.notifyDataSetChanged();
    }

}