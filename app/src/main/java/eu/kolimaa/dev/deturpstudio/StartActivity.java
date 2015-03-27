package eu.kolimaa.dev.deturpstudio;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class StartActivity extends Activity implements EditDialogFragment.TrackOperator,
        AdapterView.OnItemLongClickListener {

    static ArrayList<Track> playListTracks; //static is needed because of a MusicService

    TrackListAdapter trackListAdapter;

    EditDialogFragment newTrackDialogFragment, editTrackDialogFragment;

    FragmentManager fm = getFragmentManager();

    private Intent getExplicitMusicServiceIntent() {
        Intent service = new Intent(this, MusicService.class);
        return service;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        if (playListTracks == null) {
            playListTracks = new ArrayList<>();
        }

        startService(getExplicitMusicServiceIntent());


        trackListAdapter = new TrackListAdapter(getApplicationContext(), playListTracks);

        final ListView playlistView = (ListView) findViewById(R.id.playlist);
        final ToggleButton playToggleButton = (ToggleButton) findViewById(R.id.toggle_button_play);
        final Button stopButton = (Button) findViewById(R.id.button_stop);

        playToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    musicService.continue();
                } else {
//                    musicService.pause();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              musicService.stop();
            }
        });

        playlistView.setAdapter(trackListAdapter); //setting the adapter for playlist view
        playlistView.setOnItemLongClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {


        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

        Track currentTrack = playListTracks.get(position);

        editTrackDialogFragment = EditDialogFragment.newInstance(false);

        editTrackDialogFragment.setTrackPosition(position);
        editTrackDialogFragment.setCurrentName(currentTrack.getTrackName());
        editTrackDialogFragment.setCurrentFilePath(currentTrack.getTrackPath());

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
        Track track = new Track(name, path, getApplicationContext());
        playListTracks.add(track);
        newTrackDialogFragment = null;

        trackListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditExistingTrack(String newName, Uri newPath, int position) {
        Track currentTrack = playListTracks.get(position);

        currentTrack.setTrackName(newName);
        currentTrack.setTrackPath(newPath);

        trackListAdapter.notifyDataSetChanged();
    }

    public interface MusicPlayerController {

        public void play();

        public void pause();

        public void stop();

    }

}